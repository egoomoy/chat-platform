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

import lombok.RequiredArgsConstructor;

//	.authorizeRequests() : 요청에 대한 권한을 지정할 수 있다.
//	.anyRequest().authenticated() : 인증이 되어야 한다는 이야기이다.
//	.anonymous() : 인증되지 않은 사용자도 접근할 수 있다.
//	.fullyAuthenticated() : 완전히 인증된 사용자만 접근할 수 있다.
//	.hasRole() or hasAnyRole() : 특정 권한을 가지는 사용자만 접근할 수 있다.
//	.hasAuthority() or hasAnyAuthority() : 특정 권한을 가지는 사용자만 접근할 수 있다.
//	.hasIpAddress() : 특정 아이피 주소를 가지는 사용자만 접근할 수 있다.
//	.access() : SpEL? 표현식에 의한 결과에 따라 접근할 수 있다.
//	.not() : 접근 제한 기능을 해제
//	.permitAll() or denyAll() : 접근을 전부 허용하거나 제한한다.

@Configuration
@EnableWebSecurity
@Import({ WebSecurityConfig.DefualtSecurityConfig.class, WebSecurityConfig.ChatSecurityConfig.class })
public class WebSecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 필터체인을 두개 쓸 때, 주의 : session stateless 를 같이 설정하지 않으면, JSESSION이 발급된다.
	@Order(100)
	static class ChatSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf()
					.disable()
					.formLogin()
					.disable()
					.httpBasic()
					.disable()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.exceptionHandling()
					.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
					.accessDeniedHandler(new CustomAccessDeniedHandler())
					.and()
					.antMatcher("/chat/**");
		}
	}

	@Order(200)
	@RequiredArgsConstructor
	static class DefualtSecurityConfig extends WebSecurityConfigurerAdapter {
		private final JwtSecurityFilter jwtSecurityFilter;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf()
					.disable()
					.formLogin()
					.disable()
					.httpBasic()
					.disable()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.exceptionHandling()
					.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
					.accessDeniedHandler(new CustomAccessDeniedHandler())
					.and()
					.authorizeRequests()
					.antMatchers("/user/**")
					.permitAll()
					.antMatchers("/temp/**")
					.permitAll()
					.antMatchers("/ws/**")
					.permitAll()
					.antMatchers("/mng/**")
					.hasRole("ADMIN")
					.anyRequest()
					.denyAll()
					.and()
					.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
		}
	}
}
