package com.web.drainInfo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.drainInfo.domain.MapData;
import com.web.drainInfo.dto.CMRespDto;
import com.web.drainInfo.service.InfoServiece;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InfoController {

	private final InfoServiece infoServiece;
	
	

    @GetMapping("/search")
    public List<MapData> getStockPrice(
            @RequestParam(value="type") String type,
            @RequestParam(value="sort") String sort,
            @RequestParam(value="searchName") String searchName,
            @RequestParam(value="index") int index
    ) throws IOException, ParseException {
    	List<MapData> search = null;
	
		System.out.println("입력"+type+sort+searchName+index);
        try {
        	search = infoServiece.getSearch(type, sort, searchName, index);
			
		} catch (Exception e) {
			e.printStackTrace();
			return search;
		}

        System.out.println("출력" + search);
        
        // front에 return 하는 내용
		return search;
    }
	
    
  

}
