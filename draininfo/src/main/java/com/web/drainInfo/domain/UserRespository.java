package com.web.drainInfo.domain;

import org.apache.ibatis.annotations.Mapper;







@Mapper
public interface UserRespository {

	public int save(User user) throws Exception;
	public User findUserByUsername(String id) throws Exception;
	public int set(User user) throws Exception;
	public int deleteUserInfo(String id) throws Exception;
	
}
