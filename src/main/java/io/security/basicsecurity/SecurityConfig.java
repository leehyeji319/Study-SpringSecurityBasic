package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *packageName    : io.security.basicsecurity
 * fileName       : SecurityConfig
 * author         : modsiw
 * date           : 2023/12/31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/12/31        modsiw       최초 생성
 */

@Configuration
@EnableWebSecurity // 이게 있어야 웹 보안이 활성화된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//만든대로 여기서 초기화가 이루어진다.
		//인가 정책
		http
			.authorizeRequests()
			.anyRequest().authenticated(); // 어떤 요청에도 인증을 받로고 -> 인가정책

		//인증 정책 (폼 로그인 방식으로)
		http
			.formLogin()
		;

	}
}
