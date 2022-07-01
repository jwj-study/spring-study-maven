package com.example.jpaconnect.dto;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User{
	private UUID userSeq;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private boolean fromSocial;
	
	private Map<String, Object> attr;
	
	public ClubAuthMemberDTO(String username, String password,boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.email = username;
		
		this.password = password;
		
		this.fromSocial = fromSocial;
		
	}
	
	
//	public ClubAuthMemberDTO(String username, String password,boolean fromSocial, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
//		
//	
//		this(username, password, fromSocial, authorities);
//		this.attr = attr;
//	}



}
