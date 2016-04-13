package com.kayrin.fma.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kayrin.fma.model.FmaUser;
import com.kayrin.fma.service.FmaUserService;

@Controller
@RequestMapping("login")
@Scope("prototype")
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	
	@Autowired
	@Qualifier("userDetailsService")
	private FmaUserService userDetailsService; 
	
	
	 @RequestMapping(method=RequestMethod.GET)
	public String hello(@RequestParam(value="userid") String userid){
		logger.entry();
		FmaUser user = userDetailsService.findByUserid(userid);
		logger.debug(user);
		return "home";
	}
	
    @RequestMapping(method=RequestMethod.POST)
    public String login(@RequestParam(value="userid") String userid, @RequestParam(value="displayName") String displayName, Model model) {
    	logger.entry();
    	logger.debug("userid = " + userid); 
    	logger.debug("displayName " + displayName); 
    	FmaUser user = userDetailsService.findByUserid(userid);
		logger.debug(user);
    	if(user == null){
    		userDetailsService.create(userid, displayName);
    	}
    	//verify user is authorized
    	//check if name needs to be updated? - low priority
    	//get user roles
        return "home";
    }
}
