package aehdb.mng.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import aehdb.mng.user.model.dto.CustomUserDetailsDto;
import aehdb.mng.user.model.dto.UserDto;
import aehdb.mng.user.model.dto.UserDto.Item;

public interface UserService {
	public CustomUserDetailsDto loadUserByAccntid(String accntId) throws Exception;

	public UserDto.Response registerUser(UserDto.Request userReq) throws Exception;

	public UserDto.Response authenticateUser(UserDto.Request userReq) throws Exception;

	public List<Item> findUserList(UserDto.Request userReq, Pageable pageable) throws Exception;
	
	public UserDto.Item findByUserId(Long id) throws Exception;


}
