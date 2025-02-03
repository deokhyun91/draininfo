package com.web.drainInfo.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.web.drainInfo.exception.ValidationApiException;


@Aspect     
@Component      

public class ValidCheckAop {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	
	@Pointcut("@annotation(com.web.drainInfo.aop.ValidCheck)")
	private void enableValid() {}
		
	@Before("enableValid()")
	public void ValidBefore(JoinPoint joinpoint) {				
		Object[] args = joinpoint.getArgs();
		LOGGER.info("3-1.유효성 검사중...");
		
		for(Object arg : args){
			if(arg.getClass() == BeanPropertyBindingResult.class) {
				BindingResult bindingResult = (BindingResult)arg;			
				if(bindingResult.hasErrors()) {
					Map<String,String> errorMessage = new HashMap<String,String>();
					bindingResult.getFieldErrors().forEach(
							error -> {										
									errorMessage.put(error.getField(), error.getDefaultMessage());											
							}
					);
					 
					throw new ValidationApiException("유효성 검사실패",errorMessage);
				}
			}			
		}
	}
	
	@AfterReturning(value = "enableValid()", returning = "returnObj")
	public void afterReturn(JoinPoint joinpoint, Object returnObj) {
		LOGGER.info("3-2.유효성 검사 완료 : {}",returnObj);
	}

}            
