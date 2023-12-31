package io.security.basicsecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
			//.loginPage("/loginPage") //사용자가 로그인을 할 수 있또록 하는 페이지 (인증 안받아도 접근 가능)
			.defaultSuccessUrl("/")
			.failureUrl("/login")
			.usernameParameter("userId")
			.passwordParameter("passwd")
			.loginProcessingUrl("/login_proc") //기본은 /login
			.successHandler(new AuthenticationSuccessHandler() {
				@Override
				public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
					HttpServletResponse httpServletResponse, Authentication authentication) throws
					IOException,
					ServletException {
					//인증 결과까지 파라미터로 넘어온다.
					System.out.println("authentication : " + authentication.getName());
					//로그인 페이지 이동
					httpServletResponse.sendRedirect("/");
				}
			})
			.failureHandler(new AuthenticationFailureHandler() {
				@Override
				public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
					HttpServletResponse httpServletResponse, AuthenticationException e) throws
					IOException,
					ServletException {
					System.out.println("exception" + e.getMessage());
					//인증 실패시 로그인 페이지로 다시 이동
					httpServletResponse.sendRedirect("/login");
				}
			})
			.permitAll() // loginPage로 접근하는 사람들은 permitAll()로 모두 허용
		;

	}
}
