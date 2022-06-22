package aehdb.comm.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtSecurityFilter jwtSecurityFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable() //jwt토큰을 사용하여 쿠키에 의존하면, csrf에서 관련이 몹시 적다. 브라우저 검사를 해보면 알겠지만, 요청하는 페이지의 쿠키만 보여
	    .formLogin().disable()  
        .httpBasic().disable() 
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/ws/**").permitAll()
        .antMatchers("/chat/**").permitAll()
        .antMatchers("/sub/**").permitAll()
        .antMatchers("/pub/**").permitAll()
        .antMatchers("/mng/**").hasRole("ADMIN")
        .anyRequest().hasRole("USER");

	    http.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password1")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}
