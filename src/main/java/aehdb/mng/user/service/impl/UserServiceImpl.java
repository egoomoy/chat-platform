package aehdb.mng.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aehdb.mng.user.model.dto.CustomUserDetailsDto;
import aehdb.mng.user.model.entity.User;
import aehdb.mng.user.model.repository.UserRepository;
import aehdb.mng.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public CustomUserDetailsDto loadUserByAccntid(String accntId) throws Exception {
		User user = userRepository.findByAccntId(accntId);
		CustomUserDetailsDto customUserDetailsDto = new CustomUserDetailsDto(user);
		return customUserDetailsDto;
	}

}
