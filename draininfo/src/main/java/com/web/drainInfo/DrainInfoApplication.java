package com.web.drainInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DrainInfoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DrainInfoApplication.class, args);
	}

}
