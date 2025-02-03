package com.web.drainInfo.dto;

import com.web.drainInfo.domain.DataInsertEntity;

import lombok.Data;

@Data
public class DataInsertDto {

	private String gugun;
	private String office_nm;
	private String road_nm;
	private String office_type;
	private String tel;
	private String lat;
	private String lng;
	private String date_day;
	
	public DataInsertDto(String gugun, String office_nm, String road_nm, String office_type, String tel, String lat,
			String lng, String date_day) {
		
		this.gugun = gugun;
		this.office_nm = office_nm;
		this.road_nm = road_nm;
		this.office_type = office_type;
		this.tel = tel;
		this.lat = lat;
		this.lng = lng;
		this.date_day = date_day;
	}

	public DataInsertEntity toEntity() {
		return DataInsertEntity.builder()
							.gugun(gugun)
							.office_nm(office_nm)
							.road_nm(road_nm)
							.office_type(office_type)
							.tel(tel)
							.lat(lat)
							.lng(lng)
							.date_day(date_day)
							.build();
	}

	public DataInsertEntity toEntityUpdate() {
		return DataInsertEntity.builder()
							.gugun(gugun)
							.office_nm(office_nm)
							.road_nm(road_nm)
							.office_type(office_type)
							.tel(tel)
							.lat(lng)
							.lng(lat)
							.date_day(date_day)
							.build();
	}

	
}
