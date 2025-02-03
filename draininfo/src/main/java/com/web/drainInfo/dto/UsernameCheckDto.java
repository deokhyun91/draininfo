package com.web.drainInfo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsernameCheckDto {

	
		@NotBlank (message = "빈값일 수 없습니다")
		@Size(max = 16, min = 4)
		private String userid;

		public UsernameCheckDto(@NotBlank(message = "빈값일 수 없습니다") @Size(max = 16, min = 4) String userid) {
			super();
			this.userid = userid;
		}
		
		
}
