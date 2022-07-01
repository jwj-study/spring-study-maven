package com.example.jpaconnect.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.jpaconnect.dto.ClubAuthMemberDTO;
import com.example.jpaconnect.security.service.UserDetailsServiceImpl;

import lombok.extern.log4j.Log4j2;

//사용자 아이디 / 비밀번호 인증을 처리하는 곳 => 유효한 인증인지 확인
// 유효한지 확인하고 "Authentication" 객체를 리턴
@Log4j2
@Component
public class CustomAuthenticationManager implements AuthenticationManager{

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("=======authentication principal===========");
		
		
		log.info(authentication.getCredentials());
		
		log.info(authentication.getPrincipal());
		log.info(authentication);
		

		ClubAuthMemberDTO clubAuthMember = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

		
		
		if(!passwordEncoder.matches((String) authentication.getCredentials(), clubAuthMember.getPassword())) {
			throw new BadCredentialsException("User or password incorrect");
		}
		else {
			return new UsernamePasswordAuthenticationToken(clubAuthMember.getEmail(),clubAuthMember.getPassword(), clubAuthMember.getAuthorities()); 
		}

	}

	
	
}
