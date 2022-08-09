package aehdb.comm.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import aehdb.comm.util.CookieUtil;
import aehdb.comm.util.JwtUtil;
import aehdb.comm.util.RedisUtil;
import aehdb.mng.user.model.dto.CustomUserDetailsDto;
import aehdb.mng.user.model.dto.UserDto;
import aehdb.mng.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserService userService;
	private final CookieUtil cookieUtil;
	private final RedisUtil redisUtil;

	@Override
	@Transactional
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException, java.io.IOException {

//		final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);
		String jwt = request.getHeader("Authorization");
//		System.out.println("bearer : " + request.getHeader("Authorization"));
		
		String username = null;
		String refreshJwt = null;
		String refreshUname = null;

		try {
			if (!"".equals(jwt)) {
//				jwt = jwtToken.getValue();
				jwt = request.getHeader("Authorization").replace("Bearer ",""); 
				username = jwtUtil.getUsername(jwt);
			}
			if (username != null) {
				CustomUserDetailsDto userDetails = userService.loadUserByAccntid(username);
				if (jwtUtil.validateToken(jwt, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		} catch (ExpiredJwtException e) {
			Cookie refreshToken = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME);
			if (refreshToken != null) {
				refreshJwt = refreshToken.getValue();
			}
		} catch (Exception e) {

		}

		try {
			if (refreshJwt != null && jwt != null) {
//				refreshUname = jwtUtil.getUsername(refreshJwt);
				refreshUname = redisUtil.getData(refreshJwt);

				if (refreshUname.equals(jwtUtil.getUsername(refreshJwt))) {

					CustomUserDetailsDto userDetails = userService.loadUserByAccntid(refreshUname);

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

					UserDto.Item user = new UserDto.Item();
					user.setAccntId(refreshUname);
					String newToken = jwtUtil.generateToken(user);

					Cookie newAccessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, newToken);
//					response.addCookie(newAccessToken);
					response.setHeader("Bearer", newToken);

				}
			}
		} catch (ExpiredJwtException e) {

		} catch (Exception e) {
			e.printStackTrace();
		}

		chain.doFilter(request, response);
	}
}
