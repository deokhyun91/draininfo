package com.web.drainInfo.service;

import java.util.List;

import com.web.drainInfo.domain.MapData;



public interface InfoServiece {
	public List<MapData> getSearch(String type,String sort,String searchName,int index) throws Exception;
}
