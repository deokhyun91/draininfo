package com.web.drainInfo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.drainInfo.domain.User;
import com.web.drainInfo.domain.UserRespository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceLogin implements UserDetailsService{
	private final UserRespository userRespository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("test"+username);
		User userEntity = null;
		
		try {
			userEntity = userRespository.findUserByUsername(username);
			System.out.println("여기" + userEntity);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		if(userEntity == null) {
			throw new UsernameNotFoundException(username + "사용자 이름은 사용할 수 없습니다.");
			//어디서 찍히는지 질문
		}
		
		return new PrinsipalDetails(userEntity);
	}

}
