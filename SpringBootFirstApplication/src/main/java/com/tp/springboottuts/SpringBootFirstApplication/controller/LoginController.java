package com.tp.springboottuts.SpringBootFirstApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tp.springboottuts.SpringBootFirstApplication.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String authenticate(@RequestParam String username, @RequestParam String password, 
			ModelMap model) {
		
		if(!loginService.validate(username, password)) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		
		model.put("name", username);
		return "welcome";
	}
}
