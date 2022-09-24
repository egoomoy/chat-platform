package aehdb.mng.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aehdb.comm.exception.CustomException;
import aehdb.comm.model.mapper.CycleAvoidingMappingContext;
import aehdb.comm.model.mapper.UserMapperImpl;
import aehdb.comm.util.JwtUtil;
import aehdb.comm.util.RedisUtil;
import aehdb.mng.legacy.model.dto.LegacyDto;
import aehdb.mng.legacy.model.entity.Legacy;
import aehdb.mng.role.model.dto.RoleDto.Item;
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
	private final RedisUtil redisUtil;

	@Override
	@Transactional
	public CustomUserDetailsDto loadUserByAccntid(String accntId) throws Exception {
		Optional<User> user = Optional.ofNullable(userRepository.findByAccntId(accntId));
		CustomUserDetailsDto customUserDetailsDto = new CustomUserDetailsDto(user);
		return customUserDetailsDto;
	}

	public UserDto.Response authenticateUser(UserDto.Request userReq) throws Exception {
		Optional<User> user = Optional.ofNullable(userRepository.findByAccntId(userReq.getAccntId()));
		if (!user.isPresent() || !passwordEncoder.matches(userReq.getPassword(), user.get().getPassword())) {
			throw new CustomException.NotFoundUserInfoException();
		} else {
			UserDto.Item userItem = userMapperImpl.entitiytoItem(user.get(), new CycleAvoidingMappingContext());

			final String token = jwtUtil.generateToken(userItem);
			final String refreshJwt = jwtUtil.generateRefreshToken(userItem);

			redisUtil.setDataExpire(refreshJwt, userReq.getAccntId(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);

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
		Optional<User> existed = Optional.ofNullable(userRepository.findByAccntId(userReq.getAccntId()));
		if (existed.isPresent()) {
			throw new CustomException.AccntidExistedException();
		}

		String encodePassword = passwordEncoder.encode(userReq.getPassword());
		UserDto.Item userItem = userMapperImpl.reqToItem(userReq);
		userItem.setPassword(encodePassword);

		LegacyDto.Item lgcy = new LegacyDto.Item();
		lgcy.setId(userReq.getLegacyId());
		userItem.setLegacy(lgcy);

		User user = userMapperImpl.itemtoEntity(userItem, new CycleAvoidingMappingContext());
		userRepository.save(user);

		UserDto.Response userRes = UserDto.Response.builder().accntId(user.getAccntId()).build();

		return userRes;
	}

	@Override
	public List<UserDto.Item> findUserList(UserDto.Request userReq, Pageable pageable) throws Exception {
		List<UserDto.Item> userItemList = new ArrayList<UserDto.Item>();

		Legacy lgcy = new Legacy();
		lgcy.setId(userReq.getLegacyId());

		List<User> user = userRepository.findByLegacyId(lgcy, pageable);
		
		for (User u : user) {
			userItemList.add(userMapperImpl.entitiytoItem(u, new CycleAvoidingMappingContext()));
		}
		return userItemList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDto.Item findByUserId(Long id) throws Exception {
		UserDto.Item item = new UserDto.Item();
		Optional<User> user = Optional.ofNullable(userRepository.findOneById(id));

		if (!user.isEmpty()) {
			CustomUserDetailsDto customUserDetailsDto = new CustomUserDetailsDto(user);
			item = userMapperImpl.entitiytoItem(user.get(), new CycleAvoidingMappingContext());
			item.setRole((Collection<Item>) customUserDetailsDto.getAuthorities());
		}

		return item;
	}
}
