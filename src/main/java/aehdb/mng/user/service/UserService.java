package aehdb.mng.user.service;

import aehdb.mng.user.model.dto.CustomUserDetailsDto;

public interface UserService {
	public CustomUserDetailsDto loadUserByAccntid (String accntId) throws Exception;
	
}
