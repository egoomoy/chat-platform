package aehdb.mng.user.service;

import aehdb.mng.user.model.dto.CustomUserDetailsDto;
import aehdb.mng.user.model.dto.UserDto;

public interface UserService {
	public CustomUserDetailsDto loadUserByAccntid(String accntId) throws Exception;

	public UserDto.Response registerUser(UserDto.Request userReq) throws Exception;

	public UserDto.Response authenticateUser(UserDto.Request userReq) throws Exception;

}
