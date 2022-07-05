package com.tp.springboottuts.SpringBootSecurityDemo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IAuthUtils {
	public String getLoggedInUserName();
	public String logout(HttpServletRequest req, HttpServletResponse resp);
}
