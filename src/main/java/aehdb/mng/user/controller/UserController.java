package aehdb.mng.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aehdb.comm.model.dto.ResponseMap;
import aehdb.comm.util.CookieUtil;
import aehdb.comm.util.JwtUtil;
import aehdb.mng.user.model.dto.UserDto;
import aehdb.mng.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final CookieUtil cookieUtil;
	private final UserServiceImpl userServiceImpl;

	@GetMapping(value = "/user/login")
	public ResponseMap login(@RequestBody @Valid UserDto.Request userReq, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		try {
			UserDto.Response user = userServiceImpl.authenticateUser(userReq);
			Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, user.getToken());
			Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, user.getRefreshJwt());
			res.addCookie(accessToken);
			res.addCookie(refreshToken);
			return new ResponseMap("success", "로그인에 성공했습니다.", user);
		} catch (Exception e) {
			return new ResponseMap("error", "로그인에 실패했습니다.", e.getMessage());
		}

	}

	@PostMapping(value = "/mng/user/regist")
	public ResponseMap regist(@RequestBody @Valid UserDto.Request userReq) throws Exception {
		try {
			UserDto.Response userRes = userServiceImpl.registerUser(userReq);
			return new ResponseMap("success", "사용자 등록 완료", userRes);
		} catch (Exception e) {
			return new ResponseMap("error", "로그인에 실패했습니다.", e.getMessage());
		}
	}

	@GetMapping(value = "/loginOut")
	public String loginOut() throws Exception {
		return "loginOut";
	}
}
