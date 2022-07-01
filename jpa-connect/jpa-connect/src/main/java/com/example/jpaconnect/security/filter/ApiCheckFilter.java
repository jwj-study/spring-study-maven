package com.example.jpaconnect.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jpaconnect.security.util.TokenProvider;

import lombok.extern.log4j.Log4j2;

//OncePerRequestFilter : 요청에 한번의 필터링을 시도한다. 
@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenProvider tokenProvider;
	
	//OncePerRequestFilter 에 있는 메소드 사용, request를 통해 client의 데이터를 받아오고 response를 보내줄 수 있음, FilterChain으로 다음 filter로 넘겨줄 수 있음
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = parseBearerToken(request);	//request에서 토큰 가져오기
			log.info("Filter is running...");
			
			//token 값 null check
			if(token!=null && token.equalsIgnoreCase("null")) {
				log.info("Token is not null");
				//토큰 유효성 검사 실행 및 토큰 페이로드 가져오기
				String userId = tokenProvider.validateAndGetUserId(token);
				
				log.info("Authenticated user ID : " + userId);
				
			}
			
		} catch (Exception ex) {
			log.error("coult not set user authentication in security context", ex);
		}
	
		filterChain.doFilter(request, response);
	}
	
	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");	//헤더의 Authorization에서 값 가져오기
		log.info("=======bearerToken==========");
		log.info(bearerToken);
		
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			log.info("============(bearerToken.substring(7))==========");
			log.info(bearerToken.substring(7));	//앞의 Bearer 부분 없애고 토큰값만 가져오기
			return bearerToken.substring(7);
		}
		
		return null;	//Authorization에 bearer 토큰이 없다면 null 리턴
	}

}
