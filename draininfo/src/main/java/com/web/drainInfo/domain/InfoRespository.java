package com.web.drainInfo.domain;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface InfoRespository {

	public List<MapData> getSearch(Map<String, Object> map) throws Exception;		
}
