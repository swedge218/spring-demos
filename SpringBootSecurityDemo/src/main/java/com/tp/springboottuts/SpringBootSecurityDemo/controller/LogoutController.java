package com.tp.springboottuts.SpringBootSecurityDemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp.springboottuts.SpringBootSecurityDemo.security.AuthUtils;
import com.tp.springboottuts.SpringBootSecurityDemo.security.IAuthUtils;

public class LogoutController {

	@Autowired
	IAuthUtils authUtils;
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		return authUtils.logout(req, resp);
	}
}
