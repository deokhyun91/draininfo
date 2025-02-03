package com.web.drainInfo.service;

import com.web.drainInfo.dto.UsernameCheckDto;

public interface AuthService {

	public boolean CheckUsername(String id) throws Exception;
	public boolean deleteUser(String id) throws Exception;
}
