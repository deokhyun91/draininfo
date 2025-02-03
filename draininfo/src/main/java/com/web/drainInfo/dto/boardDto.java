package com.web.drainInfo.dto;


import com.web.drainInfo.domain.BoardEntity;

import lombok.Data;

@Data
public class boardDto {

	private int num;
	private String name;
	private String user_id;
	private String title;
	private String content;

	


	
	
	public BoardEntity toEntity() {
		return BoardEntity.builder()
					.num(num)
					.name(name)
					.user_id(user_id)
					.title(title)
					.content(content)
					.build();
	}
}
