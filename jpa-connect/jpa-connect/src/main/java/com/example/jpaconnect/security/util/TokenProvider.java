package com.example.jpaconnect.security.util;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;

//bean에 등록하기 위해 @Service 설정
@Log4j2
@Service
public class TokenProvider {
	private static final String SECRET_KEY = "japStudy123";
	
	public String create() {
		
		return null;
	}
	
	//위조 되었는지 시크릿 키를 통해 확인
	public String validateAndGetUserId(String token) {
		//claim : 페이로드 부분에 담는 토큰의 정보 중 한 조각
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}
