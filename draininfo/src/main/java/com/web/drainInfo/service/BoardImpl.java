package com.web.drainInfo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.drainInfo.domain.BoardEntity;
import com.web.drainInfo.domain.BoardRespository;
import com.web.drainInfo.dto.boardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class BoardImpl implements Board{

	private final BoardRespository boardRespository;
	
	@Override
	public boolean addBoard(boardDto boardDto) throws Exception {
		return boardRespository.saveBoard(boardDto.toEntity()) > 0;
		
		
	}

	@Override
	public List<BoardEntity> getBoard() throws Exception {
		
		return boardRespository.getBoard();
	}

	@Override
	public boolean updateBoard(boardDto boardDto) throws Exception {
		return boardRespository.updateBoard(boardDto.toEntity()) > 0;
	}

	@Override
	public boolean deleteBoard(int num) throws Exception {
		return boardRespository.deleteBoard(num) > 0;
	}

}
