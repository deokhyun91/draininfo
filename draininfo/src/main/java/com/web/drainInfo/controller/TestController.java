package com.web.drainInfo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class TestController implements ErrorController{
 
	@GetMapping("/api/hello")
    public String test() {
        return "Hsi, world!";
    }
	@GetMapping("/test")
    public String test2() {
        return "NewFile";
    }
	
	 @GetMapping(value =  {"/", "/infoboard", "/infoboard/:maintype/:sort/:mainsearchName/:index", "/board", "/myboard", "/board_v", "/board_w", "/board_update", "/join", "/login", "/mypage" ,"/userUpdate", "/logout", "/error"})
	    public String forward() {
	        return "forward:/index.html";
	    }
}
