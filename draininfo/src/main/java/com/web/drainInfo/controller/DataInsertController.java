package com.web.drainInfo.controller;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.drainInfo.domain.MapData;
import com.web.drainInfo.dto.CMRespDto;
import com.web.drainInfo.dto.DataInsertDto;
import com.web.drainInfo.service.DataInsertService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DataInsertController {

	private final DataInsertService dataInsertService;
	
	
	  
	  
	@GetMapping("/apiinsert")
    public ResponseEntity<?> callApi() throws IOException{
		boolean status = false;
		try {
		
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/BusanDrainInfoService/getDrainInfo"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=1uz%2BDEK3qqLJlnYyIcKH5%2B0anQwHKOTTN0C7Dcw2w5BeHLi5G3vA%2B367Sgwk8CyJR8%2FYD2ZyAsIE0Psf7AzoUg%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("5000", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	  
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
	 
	        JSONObject response  = (JSONObject)jsonObject.get("response");
	  
	        JSONObject body  = (JSONObject)response.get("body");
	        JSONObject items  = (JSONObject)body.get("items");
	        JSONArray item  = (JSONArray)items.get("item");
	        for(int i = 0; i < item.size(); i++) {
	        	  JSONObject object = (JSONObject)item.get(i);
	        	  String gugun = (String) object.get("gugun");
	       	   	  String office_nm = (String) object.get("office_nm");
			      String road_nm = (String) object.get("road_nm");
			      String office_type = (String) object.get("office_type");
			      String tel = (String) object.get("tel");
			      String lat = (String) object.get("lat");
			      String lng = (String) object.get("lng");
			      String date_day = (String) object.get("date_day");
	       	   	  DataInsertDto dataInsertDto = new DataInsertDto(gugun, office_nm, road_nm, office_type, tel, lat, lng, date_day);
	       	   	  status = dataInsertService.addData(dataInsertDto);
	        }
		} catch (Exception e) {
			  e.printStackTrace();
            return ResponseEntity.ok().body(new CMRespDto<>(-1, "데이터저장실패", status));
		}
		
		
        return ResponseEntity.ok().body(new CMRespDto<>(1, "데이터저장성공", status));

    }
}
