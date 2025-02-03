package com.web.drainInfo.service;

import org.springframework.stereotype.Service;

import com.web.drainInfo.domain.UserRespository;
import com.web.drainInfo.dto.SignupReqDto;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor     
public class PrincipalDetailsServiceImpl implements PrincilpalDetailsService {
	
	private final UserRespository userRespository;
	
	@Override
	public boolean addUser(SignupReqDto signupReqDto) throws Exception {
		return userRespository.save(signupReqDto.toEntity()) > 0;
	}
	
	public boolean setUser(SignupReqDto signupReqDto) throws Exception{
		
		
		return userRespository.set(signupReqDto.toEntity()) > 0;
		
	}

}
