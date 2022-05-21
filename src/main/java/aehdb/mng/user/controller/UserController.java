package aehdb.mng.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import aehdb.comm.model.dto.ResponseMap;
import aehdb.comm.util.CookieUtil;
import aehdb.comm.util.JwtUtil;
import aehdb.mng.user.model.dto.UserDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final JwtUtil jwtUtil;
	private final CookieUtil cookieUtil;

	@GetMapping(value = "/login")
	public ResponseMap login(@ModelAttribute UserDto userDto, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		try {
			UserDto user = new UserDto();
			user.setAccntId("aaa");
			final String token = jwtUtil.generateToken(user);
			final String refreshJwt = jwtUtil.generateRefreshToken(user);
			Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
			Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
			res.addCookie(accessToken);
			res.addCookie(refreshToken);
			return new ResponseMap("success", "로그인에 성공했습니다.", token);
		} catch (Exception e) {
			return new ResponseMap("error", "로그인에 실패했습니다.", e.getMessage());
		}

	}

	@GetMapping(value = "/loginOut")
	public String loginOut() throws Exception {
		return "loginOut";
	}
}
