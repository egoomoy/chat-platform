package aehdb.mng.user.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aehdb.comm.exception.CustomException;
import aehdb.comm.model.mapper.CycleAvoidingMappingContext;
import aehdb.comm.model.mapper.UserMapperImpl;
import aehdb.comm.util.JwtUtil;
import aehdb.mng.user.model.dto.CustomUserDetailsDto;
import aehdb.mng.user.model.dto.UserDto;
import aehdb.mng.user.model.entity.User;
import aehdb.mng.user.model.repository.UserRepository;
import aehdb.mng.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapperImpl userMapperImpl;
	private final JwtUtil jwtUtil;

	@Override
	@Transactional
	public CustomUserDetailsDto loadUserByAccntid(String accntId) throws Exception {
		Optional<User> user = userRepository.findByAccntId(accntId);
		CustomUserDetailsDto customUserDetailsDto = new CustomUserDetailsDto(user);
		return customUserDetailsDto;
	}

	public UserDto.Response authenticateUser(UserDto.Request userReq) throws Exception {
		Optional<User> user = userRepository.findByAccntId(userReq.getAccntId());
		if (!user.isPresent() || !passwordEncoder.matches(userReq.getPassword(), user.get().getPassword())) {
			throw new CustomException.NotFoundUserInfoException();
		} else {
			UserDto.Item userItem = userMapperImpl.entitiytoItem(user.get(), new CycleAvoidingMappingContext());

			final String token = jwtUtil.generateToken(userItem);
			final String refreshJwt = jwtUtil.generateRefreshToken(userItem);

			UserDto.Response userRes = UserDto.Response.builder()
					.accntId(userItem.getAccntId())
					.userNm(userItem.getUserNm())
					.token(token)
					.refreshJwt(refreshJwt)
					.build();

			return userRes;
		}
	}

	public UserDto.Response registerUser(UserDto.Request userReq) throws Exception {
		Optional<User> existed = userRepository.findByAccntId(userReq.getAccntId());
		if (existed.isPresent()) {
			throw new CustomException.AccntidExistedException();
		}

		String encodePassword = passwordEncoder.encode(userReq.getPassword());

		UserDto.Item userItem = userMapperImpl.reqToItem(userReq);
		userItem.setPassword(encodePassword);

		User user = userMapperImpl.itemtoEntity(userItem, new CycleAvoidingMappingContext());
		userRepository.save(user);

		UserDto.Response userRes = UserDto.Response.builder().accntId(user.getAccntId()).build();

		return userRes;
	}

}
