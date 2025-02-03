package com.web.drainInfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.drainInfo.domain.InfoRespository;
import com.web.drainInfo.domain.MapData;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class InfoServiceImpl implements InfoServiece{

	private final InfoRespository infoRespository;

	@Override
	public List<MapData> getSearch(String type, String sort, String searchName, int index) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);		
		map.put("searchName", searchName);
		map.put("sort", sort);
		
		List<MapData> getSearch = infoRespository.getSearch(map);
		List<MapData> outSearch = new ArrayList<MapData>();
		
		int start = index*20;
		int end = index*20+20;

		
		if(getSearch.size()>0) {
			int size = getSearch.size();
			
			while(true) {
				try {
					outSearch.add(MapData.builder()
							.size(size)
							.data_num(getSearch.get(start).getData_num())
							.gugun(getSearch.get(start).getGugun())
							.office_nm(getSearch.get(start).getOffice_nm())
							.road_nm(getSearch.get(start).getRoad_nm())
							.office_type(getSearch.get(start).getOffice_type())
							.tel(getSearch.get(start).getTel())
							.lat(getSearch.get(start).getLat())
							.lng(getSearch.get(start).getLng())
							.date_day(getSearch.get(start).getDate_day())
							.build()
					);
					
					start++;
					if(start==end) {
						break;
					}
				}
				catch(Exception e) {
					break;
				}
			}			
		}
		
		return outSearch;
	}

}
