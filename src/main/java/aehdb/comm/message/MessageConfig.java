package aehdb.comm.message;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
public class MessageConfig {
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		// 메세지 프로퍼티파일의 위치와 이름을 지정한다.
		source.setBasename("classpath:/messages/message");
		// 기본 인코딩을 지정한다.
		source.setDefaultEncoding("UTF-8");
		// 프로퍼티 파일의 변경을 감지할 시간 간격을 지정한다.
		source.setCacheSeconds(600);
		// 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREA);
		resolver.setCookieName("lang");
		return resolver;
	}
	
	@Bean
	public MessageSourceAccessor messageSourceAccesso () {
		return new MessageSourceAccessor(messageSource());
	}

	@Bean 
	public MessageUtil messageUtil() {
		// TODO Auto-generated method stub
		MessageUtil messageUtil = new MessageUtil();
		messageUtil.setMessageSourceAccessor(messageSourceAccesso());
		return messageUtil;
	}
}
