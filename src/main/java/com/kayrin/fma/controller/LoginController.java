package com.kayrin.fma.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());

	@RequestMapping("/")
	public String  helloWorld(){
		logger.debug("cdw - test from index");
		
		return "index";
	}

}
