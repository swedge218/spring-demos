package com.tp.springboottuts.SpringBootFirstApplication.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	public boolean validate(String username, String password) {
		String userId = "Ola";
		String userPass = "Pass";
		
		return username.equalsIgnoreCase(userId)
				&& password.equalsIgnoreCase(userPass);
	}
}
