package com.web.drainInfo.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataInsertEntity {

	private String gugun;
	private String office_nm;
	private String road_nm;
	private String office_type;
	private String tel;
	private String lat;
	private String lng;
	private String date_day;
	   
}
