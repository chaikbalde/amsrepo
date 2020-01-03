package com.hub4u.ams.web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private static Logger logger = LogManager.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String home() {
		logger.debug("home() - Intro ...");
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		logger.debug("login() - Intro ...");
		return "login";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		logger.debug("accessDenied() - Intro ...");
		return "403";
	}
	

}
