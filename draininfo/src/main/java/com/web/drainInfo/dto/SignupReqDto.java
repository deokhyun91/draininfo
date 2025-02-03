package com.web.drainInfo.dto;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.web.drainInfo.domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupReqDto {
	
	private int num;
	
	private String provider;
	
	@NotBlank
	@Pattern(regexp = "^[가-힇]*$", message = "한글만 입력 가능합니다.")
	private String name;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{4,12}$", message = "영어만 입력 가능합니다.")
	private String id;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,16}$")
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]+$", message = "숫자만 입력 가능합니다.")
	private String phone;
	
	private String address;

	
	
	public User toEntity() {
		return User.builder()
					.num(num)
					.provider("ROLE_USER")
					.name(name)
					.id(id)
					.password(new BCryptPasswordEncoder().encode(password))
					.phone(phone)
					.address(address)
					.build();
	}
}
