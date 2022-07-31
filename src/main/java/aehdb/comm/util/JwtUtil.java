package aehdb.comm.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import aehdb.comm.model.mapper.UserMapperImpl;
import aehdb.mng.user.model.dto.CustomUserDetailsDto;
import aehdb.mng.user.model.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Component
@RequiredArgsConstructor
public class JwtUtil {
	private final UserMapperImpl userMapperImpl;


	public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
	public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;

	final static public String ACCESS_TOKEN_NAME = "accessToken";
	final static public String REFRESH_TOKEN_NAME = "refreshToken";

	@Value("${spring.jwt.secret}")
	private String SECRET_KEY;

	private Key getSigningKey(String secretKey) {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public Claims extractAllClaims(String token) throws ExpiredJwtException {
		return Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(token).getBody();
	}

	// 유니크 키가 될 유저 정보를 사번으로 구현
	public String getUsername(String token) {
		String accntId = extractAllClaims(token).get("accntId", String.class);
		return accntId;
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = extractAllClaims(token).getExpiration();
		return expiration.before(new Date());
	}

	public String generateToken(UserDto.Item userItem) {
		return doGenerateToken(userItem, TOKEN_VALIDATION_SECOND);
	}

	public String generateRefreshToken(UserDto.Item userItem) {
		return doGenerateToken(userItem, REFRESH_TOKEN_VALIDATION_SECOND);
	}

	public String doGenerateToken(UserDto.Item userItem, long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("accntId", userItem.getAccntId());
		String jwt = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256).compact();
		return jwt;
	}

	public Boolean validateToken(String token, CustomUserDetailsDto userDetails) {
		try {
			final String username = getUsername(token);
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}
}