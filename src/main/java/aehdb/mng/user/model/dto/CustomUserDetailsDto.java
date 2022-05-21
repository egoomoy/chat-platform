package aehdb.mng.user.model.dto;

import java.util.Collection;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import aehdb.mng.user.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDetailsDto implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8700608249535039962L;
	private User user;
	public CustomUserDetailsDto(User user) {
		this.user = user;
	}
  
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.user.getRole().stream()
		.map(role -> new SimpleGrantedAuthority(role.getRoleNm()))
		.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getAccntId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
