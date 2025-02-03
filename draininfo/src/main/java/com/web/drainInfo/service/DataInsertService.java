package com.web.drainInfo.service;

import com.web.drainInfo.dto.DataInsertDto;

public interface DataInsertService {

	public boolean addData(DataInsertDto dataInsertDto ) throws Exception;
	
}
