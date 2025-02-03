package com.web.drainInfo.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.drainInfo.aop.ValidCheck;
import com.web.drainInfo.dto.CMRespDto;
import com.web.drainInfo.dto.SignupReqDto;
import com.web.drainInfo.dto.UsernameCheckDto;
import com.web.drainInfo.service.AuthService;
import com.web.drainInfo.service.PrincipalDetailsServiceImpl;
import com.web.drainInfo.service.PrinsipalDetails;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j

public class AuthController {

	
	private final PrincipalDetailsServiceImpl principalDetailsService;
	private final AuthService authService;
	
	@PostMapping("/join")
	public ResponseEntity<?> signUp(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult){
		boolean status = false;

	
		try {
			status = principalDetailsService.addUser(signupReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "회원가입실패", status));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입성공", status));
		
		
	}
	
	
	@GetMapping("/join/userid")
	public ResponseEntity<?> checkUsername(@RequestParam(value="id") @NotBlank(message = "빈값일 수 없습니다") @Size(max = 16, min = 4) String id){
		System.out.println(id);
		
		

		boolean status = false;
		try {
			status = authService.CheckUsername(id);
			System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "서버오류", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입가능여부", status));
	}
	
	
	@GetMapping("/principal")
	public ResponseEntity<?> getPrincipal(@AuthenticationPrincipal PrinsipalDetails prinsipalDetails){
		if(prinsipalDetails == null) {
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "prcipal is null", null));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "sucess load", prinsipalDetails.getUser()));
	}
	
	
	//수정

	@PutMapping("/user/update")
	public ResponseEntity<?> setUser(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult){
		System.out.println(signupReqDto);
		boolean status = false;
		try {
			status = principalDetailsService.setUser(signupReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "업데이트 실패", status));
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "업데이트 성공", status));
	}
	
	@DeleteMapping("/delete/userid")
	public ResponseEntity<?> DeleteUser(@RequestParam(value="id") String id){
		boolean status = false;
		
		try {
			status = authService.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "삭제 실패", status));
		}

		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "삭제 성공", status));
	}
	

}
