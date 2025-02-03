package com.web.drainInfo.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class ValidationApiException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private Map<String ,String> errorMap;
	
	public ValidationApiException() {
		this("error",new HashMap<String,String>());
	}
	
	public ValidationApiException(String message) {
		this(message,new HashMap<String,String>());
	}
	
	public ValidationApiException(String message,Map<String,String> errorMap) {		
		super(message);		
		this.errorMap = errorMap;
	}
}
