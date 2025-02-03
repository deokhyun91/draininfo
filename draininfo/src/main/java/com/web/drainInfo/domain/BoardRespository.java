package com.web.drainInfo.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper 
public interface BoardRespository {

	public int saveBoard(BoardEntity boardEntity) throws Exception;
	public List<BoardEntity> getBoard() throws Exception;
	public int updateBoard(BoardEntity boardEntity) throws Exception;
	public int deleteBoard(int num) throws Exception;
}
