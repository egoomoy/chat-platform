package aehdb.comm.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@Import({ WebSecurityConfig.DefualtSecurityConfig.class,WebSecurityConfig.ChatSecurityConfig.class  })
public class WebSecurityConfig {
	
	@Order(100)
	static class ChatSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			   http.csrf().disable() 
			    .formLogin().disable()  
		        .httpBasic().disable() 
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .authorizeRequests()
		        .antMatchers("/ws/**").permitAll() // 소켓연결
		        .antMatchers("/chat/**").permitAll() // 방 생성 
		        .antMatchers("/sub/**").permitAll() // 메시지 전송
		        .antMatchers("/pub/**").permitAll(); // 메시지 수신
		}
	}


	@Order(200)
	@RequiredArgsConstructor
	static class DefualtSecurityConfig extends WebSecurityConfigurerAdapter {
		private final JwtSecurityFilter jwtSecurityFilter;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			   http.csrf().disable() //jwt토큰을 사용하여 쿠키에 의존하면, csrf에서 관련이 몹시 적다. 브라우저 검사를 해보면 알겠지만, 요청하는 페이지의 쿠키만 보여
			    .formLogin().disable()  
		        .httpBasic().disable() 
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .authorizeRequests()
		        .antMatchers("/mng/**").permitAll()
		        .anyRequest().hasRole("USER");
			    http.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
		}

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
