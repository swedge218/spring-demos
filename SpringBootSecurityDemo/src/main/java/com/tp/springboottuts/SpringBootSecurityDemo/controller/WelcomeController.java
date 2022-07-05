package com.tp.springboottuts.SpringBootSecurityDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.springboottuts.SpringBootSecurityDemo.security.IAuthUtils;

@Controller
public class WelcomeController {

	@Autowired
	IAuthUtils authUtils;
	
	@Value("${welcome.message}")
	private String landingMessage;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", authUtils.getLoggedInUserName());
		return "welcome";
	}
	
	@GetMapping("/landing")
	@ResponseBody
	public String showLandingMessage() {
		return landingMessage;
	}
	
}
