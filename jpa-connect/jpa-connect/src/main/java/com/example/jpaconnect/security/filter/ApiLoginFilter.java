package com.example.jpaconnect.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.jpaconnect.security.util.TokenProvider;

import lombok.extern.log4j.Log4j2;



//AbstractAuthenticationProcessingFilter : 인증 처리 기능을 하기위해 상속
@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter{
	
	@Autowired
	private TokenProvider tokenProvider;
	
	
	//로그인 api로 요청했을 때 필터가 작동 함 => 테스트로 /login 설정
	public ApiLoginFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info("=========ApiLoginFilter==========");
		
		String email = request.getParameter("email");	//email 이름으로 되어있는 value return
		String pw = request.getParameter("pw");
		
		//UsernamePasswordAuthenticationToken은 추후 인증이 끝나고 SecurityContextHolder에 등록될 인증 객체이다.
		//아직 인증되지 않은 토큰을 생성
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pw);
				
		
		return getAuthenticationManager().authenticate(authToken);
	}
	
	
	
	
	
	 
}
