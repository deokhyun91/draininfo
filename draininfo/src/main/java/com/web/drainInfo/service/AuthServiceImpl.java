package com.web.drainInfo.service;

import org.springframework.stereotype.Service;

import com.web.drainInfo.domain.UserRespository;
import com.web.drainInfo.dto.UsernameCheckDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRespository userRespository;
	@Override
	public boolean CheckUsername(String id) throws Exception {
		
		return userRespository.findUserByUsername(id) == null;
	}
	@Override
	public boolean deleteUser(String id) throws Exception {
		return userRespository.deleteUserInfo(id) > 0;
	}

}
