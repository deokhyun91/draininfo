package com.web.drainInfo.service;


import java.util.List;

import com.web.drainInfo.domain.BoardEntity;
import com.web.drainInfo.dto.boardDto;

public interface Board {

	public boolean addBoard(boardDto boardDto) throws Exception;
	public List<BoardEntity> getBoard() throws Exception;
	public boolean updateBoard(boardDto boardDto) throws Exception;
	public boolean deleteBoard(int num) throws Exception;
}
