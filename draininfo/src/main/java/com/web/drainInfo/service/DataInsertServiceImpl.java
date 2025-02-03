package com.web.drainInfo.service;

import org.springframework.stereotype.Service;

import com.web.drainInfo.domain.DataInsertRespository;

import com.web.drainInfo.dto.DataInsertDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataInsertServiceImpl implements DataInsertService{

	private final DataInsertRespository dataInsertRespository;
	
	@Override
	public boolean addData(DataInsertDto dataInsertDto) throws Exception {
		return dataInsertRespository.save(dataInsertDto.toEntity()) > 0;
	}



}
