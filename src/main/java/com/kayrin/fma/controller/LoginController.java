package com.kayrin.fma.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
@Scope("prototype")
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	
    @RequestMapping(method=RequestMethod.POST)
    public String login(@RequestParam() String userid, String username, Model model) {
    	logger.entry();

        return "index";
    }
}
