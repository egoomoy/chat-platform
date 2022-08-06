package aehdb.comm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import aehdb.comm.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@Import({ WebSecurityConfig.DefualtSecurityConfig.class,WebSecurityConfig.ChatSecurityConfig.class  })
public class WebSecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Order(200)
	static class ChatSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			   http.csrf().disable() 
			    .formLogin().disable()  
		        .httpBasic().disable() 
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .authorizeRequests()
		        .antMatchers("/chat/*").permitAll(); // 방 생성 
		}
	}


	@Order(100)
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
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//		        .accessDeniedHandler(new CustomAccessDeniedHandler())
		        .and()
		        .authorizeRequests()
		        .antMatchers("/user/*").permitAll()
		        .antMatchers("/mng/*").hasRole("ADMIN")
			   .and().addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
		}
	}
}
