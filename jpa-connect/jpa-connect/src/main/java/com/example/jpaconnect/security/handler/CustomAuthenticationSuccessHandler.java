package com.example.jpaconnect.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private String defaultSuccessUrl = "/";
	
	public CustomAuthenticationSuccessHandler(String defaultSuccessUrl) {
		 this.defaultSuccessUrl = defaultSuccessUrl;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("========authentication successHandler============");
		
		log.info(defaultSuccessUrl);
		
		
		
		String pw = request.getParameter("pw");
		
		log.info(pw);
		
//		response.sendRedirect(defaultSuccessUrl);
		
	}

}
