package com.example.jpaconnect.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jpaconnect.security.filter.ApiCheckFilter;
import com.example.jpaconnect.security.filter.ApiLoginFilter;
import com.example.jpaconnect.security.handler.CustomAuthenticationFailureHandler;
import com.example.jpaconnect.security.handler.CustomAuthenticationManager;
import com.example.jpaconnect.security.handler.CustomAuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j2;


//설정에 필요한 것 JWT 검증, web security(api 권한, csrf 설정, 로그인 페이지 설정), 로그인 성공 실패 handler
@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {	
	//비밀번호를 암호화하기위해 Encoder
	@Autowired
	private CustomAuthenticationManager customAuthenticationManager;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//http 시큐리티 빌더
		http
		.formLogin()
		.and()
		.cors()	//Cross-Origin Resource Sharing : 다른 출처의 자원을 공유할 수 있도록 설정, 
					//WebMvcConfig 파일에 WebMvcConfigurer를 implements해서 설정할 계획
		.and()
		.csrf() //csrf(Cross site Request forgery) : 정상적인 사용자가 의도치 않은 위조요청을 보내는 것으로 서버가 csrf 토큰이 필요함 
				//jwt토큰은 stateless 하기 때문에 서버에서 저장하는 불필요한 csrf 토큰을 저장할 필요가 없기 때문에 disable
		.disable()
		.httpBasic()	//Http 에서 제공하는 기본 인증 로그인창이 뜸 => 사용 x
		.disable()
		.sessionManagement()	//서버에 인증정보를 담지 않는다 => STATELESS
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()	//각 경로 path 별 권한 처리
		.antMatchers("/login", "/").permitAll();	// /login/** 과 /auth/** 경로는 인증 안해도 됨.
		
		//UsernamePasswordAuthenticationFilter Form 기반의 인증 방식으로 아이디 패스워드 데이터를  파싱하여 인증 요청을 위임하는 필터 
		http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean 
	ApiCheckFilter apiCheckFilter() {
		
		return new ApiCheckFilter();
	}
	
	
	@Bean
	public ApiLoginFilter apiLoginFilter() throws Exception{
		ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login");
		apiLoginFilter.setAuthenticationManager(customAuthenticationManager);
		apiLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler("/"));
		apiLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler("/login"));
		return apiLoginFilter;
		
	}
	
}
