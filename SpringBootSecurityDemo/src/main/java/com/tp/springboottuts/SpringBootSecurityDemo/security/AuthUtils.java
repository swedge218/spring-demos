package com.tp.springboottuts.SpringBootSecurityDemo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils implements IAuthUtils {
	
	public String getLoggedInUserName() {
		Object principal = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		if(principal instanceof UserDetails) {
			System.out.println(principal.toString());
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
	
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(req, resp, auth);
		}
		
		return "redirect:/";
	}
	
}
