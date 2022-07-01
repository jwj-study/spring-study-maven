package com.example.jpaconnect.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jpaconnect.dto.ClubAuthMemberDTO;
import com.example.jpaconnect.model.ClubMember;
import com.example.jpaconnect.repository.ClubMemberRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClubMemberRepository clubMemberRepository;
	
	@Override
	public ClubAuthMemberDTO loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("========userDetailsService==========");
		
		Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);
		
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("Check User Email");
		}
		
		ClubMember clubMember = result.get();
		
		log.info("=======clubMember=======");
		log.info(clubMember);
		
		
		ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
				clubMember.getEmail(),
				clubMember.getPassword(),
				clubMember.isFromSocial(),
				clubMember.getRoleSet().stream()
					.map(role-> new SimpleGrantedAuthority("ROLE_"+role.name()))
					.collect(Collectors.toSet())
				);
		
		clubAuthMember.setName(clubMember.getName());
		clubAuthMember.setUserSeq(clubMember.getUserSeq());
		
		return clubAuthMember;
	}


}
