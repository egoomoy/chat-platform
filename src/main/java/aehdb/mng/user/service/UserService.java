package aehdb.mng.user.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import aehdb.mng.user.model.dto.CustomUserDetailsDto;
import aehdb.mng.user.model.dto.UserDto;
import aehdb.mng.user.model.dto.UserDto.Item;

public interface UserService {
	public CustomUserDetailsDto loadUserByAccntid(String accntId) throws Exception;

	public UserDto.Response registerUser(UserDto.Request userReq) throws Exception;

	public UserDto.Response authenticateUser(UserDto.Request userReq) throws Exception;

	public List<Item> selectUserList(Long legacyId, Pageable pageable) throws Exception;

}
