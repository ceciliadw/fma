package com.kayrin.fma.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class WelcomeController {
	
	private static final Logger logger = LogManager.getLogger(WelcomeController.class.getName());
	
    @RequestMapping(method=RequestMethod.GET)
    public String welcome(Model model) {
    	logger.entry();

        return "index";
    }

}
