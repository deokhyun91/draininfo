package com.web.drainInfo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.drainInfo.domain.BoardEntity;
import com.web.drainInfo.domain.MapData;
import com.web.drainInfo.dto.CMRespDto;
import com.web.drainInfo.dto.boardDto;
import com.web.drainInfo.service.AuthService;
import com.web.drainInfo.service.Board;
import com.web.drainInfo.service.PrincipalDetailsServiceImpl;


import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
public class BoardController {
	
	private final Board board;
	
	@PostMapping("/board/write")
	public ResponseEntity<?> boardWrite(@RequestBody boardDto boardDto){
		System.out.println(boardDto);
		boolean status = false;

	
		try {
			status = board.addBoard(boardDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "글 작성 실패", status));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "글 작성 성공", status));
		
		
	}
	
	@GetMapping("/boarddata")
	public ResponseEntity<?> boardList(){
    	List<BoardEntity> status = null;
		
		try {
			status = board.getBoard();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "글 데이터 불러오기 실패", status));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "글 데이터 불러오기 성공", status));
		
		
	}
	
	@PutMapping("/board/update")
	public ResponseEntity<?> boardUpdate(@RequestBody boardDto boardDto){
    	boolean status = false;
		System.out.println(boardDto);
		try {
			status = board.updateBoard(boardDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "글 수정 실패", status));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "글 수정 성공", status));
		
		
	}
	
	@DeleteMapping("deleteBoard/boardnum")
	public ResponseEntity<?> DeleteUser(@RequestParam(value="num") int num){
		boolean status = false;
		System.out.println( num);
		try {
			status = board.deleteBoard(num);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "삭제 실패", status));
		}

		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "삭제 성공", status));
	}
	
	
}
