package com.web.drainInfo.domain;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface DataInsertRespository {

	public int save(DataInsertEntity dataInsertEntity) throws Exception;
	
}
