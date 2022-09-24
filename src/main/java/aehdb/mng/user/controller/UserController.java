package aehdb.mng.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aehdb.comm.model.dto.ResponseMap;
import aehdb.comm.util.CookieUtil;
import aehdb.comm.util.JwtUtil;
import aehdb.mng.user.model.dto.UserDto;
import aehdb.mng.user.model.dto.UserDto.Response.ResponseBuilder;
import aehdb.mng.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final CookieUtil cookieUtil;
	private final UserServiceImpl userServiceImpl;

	@PostMapping(value = "/login")
	public ResponseMap login(@RequestBody @Valid UserDto.Request userReq, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		try {
			UserDto.Response userRes = userServiceImpl.authenticateUser(userReq);
//			Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, userRes.getToken());
			Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, userRes.getRefreshJwt());
//			res.addCookie(accessToken);
			res.addCookie(refreshToken);
			res.setHeader("Bearer", userRes.getToken());
			return new ResponseMap(200, "로그인에 성공했습니다.", userRes);
		} catch (Exception e) {
			return new ResponseMap(500, e.getMessage(), null);
		}
	}

	@PostMapping(value = "/mng/user")
	public ResponseMap registerUser(@RequestBody @Valid UserDto.Request userReq) throws Exception {
		try {
			UserDto.Response userRes = userServiceImpl.registerUser(userReq);
			return new ResponseMap(200, "사용자 등록에 성공했습니다.", userRes);
		} catch (Exception e) {
			return new ResponseMap(500, e.getMessage(), null);
		}
	}

	@PostMapping(value = "/mng/users")
	public ResponseMap getUsers(@RequestBody @Valid UserDto.Request userReq, Pageable pageable) throws Exception {
//		public ResponseMap getUsers(@RequestParam @NotNull(message="must not be null") Long legacyId, Pageable pageable) throws Exception {
		List<UserDto.Response> userResList = new ArrayList<UserDto.Response>();
		List<UserDto.Item> userItemList = userServiceImpl.findUserList(userReq, pageable);

		for (UserDto.Item uItem : userItemList) {
			ResponseBuilder ub = UserDto.Response.builder();
			ub.accntId(uItem.getAccntId())
					.userNm(uItem.getUserNm())
					.legacyNm(uItem.getLegacy().getLgcyNm())
					.id(uItem.getId());
			userResList.add(ub.build());
		}

		return new ResponseMap(200, "", userResList);
	}

	@GetMapping("/mng/user/{accntId}")
	public ResponseMap getUser(@PathVariable("accntId") Long id) throws Exception {
		UserDto.Item uItem = userServiceImpl.findByUserId(id);
		ResponseBuilder ub = UserDto.Response.builder();
		if (uItem != null & uItem.getLegacy() != null) {
			ub.accntId(uItem.getAccntId())
					.userNm(uItem.getUserNm())
					.id(uItem.getId())
					.role(uItem.getRole())
					.legacyNm(uItem.getLegacy().getLgcyNm());
		}

		UserDto.Response userRes = ub.build();
		return new ResponseMap(200, "", userRes);
	}

	@GetMapping(value = "/loginOut")
	public String loginOut() {
		return "loginOut";
	}
}
