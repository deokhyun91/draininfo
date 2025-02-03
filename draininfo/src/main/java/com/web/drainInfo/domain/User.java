package com.web.drainInfo.domain;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	private int num;
	private String provider;
	private String name;
	private String id;
	private String password;
	private String phone;
	private String address;
	
	
	private LocalDateTime create_day;
	
	private LocalDateTime update_day;
	

		public List<String> getUserRoles(){
			if(provider == null || provider.isBlank()) {
				return new ArrayList<>();
			}
			return Arrays.asList(provider.replaceAll(" ", "").split(","));
		}
}
