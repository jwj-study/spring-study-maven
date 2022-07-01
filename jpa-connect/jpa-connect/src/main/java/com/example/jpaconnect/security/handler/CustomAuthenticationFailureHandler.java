package com.example.jpaconnect.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
	private String defaultFailureUrl = "/login-error";
	
	public CustomAuthenticationFailureHandler(String defaultFilureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("=========Authentication Failure Handler");
		
	}

}