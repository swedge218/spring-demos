package com.tp.springboottuts.SpringBootSecurityDemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


public class ErrorController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception ex) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("message", ex.getLocalizedMessage());
//		mv.addObject("trace", ex.getStackTrace());
		
		mv.setViewName("error");
		return mv;
	}
}
