package com.web.drainInfo.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MapData {

	private int size;
	private int data_num;	
	private String gugun;
	private String office_nm;	
	private String road_nm;
	private String office_type;	
	private String tel;	
	private double lat;	
	private double lng;	
	private String date_day;	
	
}
