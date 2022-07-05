package com.tp.springboottuts.jpademo.runner;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tp.springboottuts.jpademo.model.User;
import com.tp.springboottuts.jpademo.repository.UserRepository;

//@Component
public class UserCommandLineRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	public static final Logger logger = LoggerFactory.getLogger(UserCommandLineRunner.class);
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		userRepository.save(new User("Leke", "Admin"));
		userRepository.save(new User("Temi", "Editor"));
		userRepository.save(new User("Akin", "PM"));
		userRepository.save(new User("Roto", "PM"));
		
		logger.info("-------------------------------");
		logger.info("Finding all users");
		logger.info("-------------------------------");
		for(User user: userRepository.findAll()) {
			logger.info(user.toString());
		}
		
		logger.info("-------------------------------");
		logger.info("Finding all users with role PM");
		logger.info("-------------------------------");
		for(User user: userRepository.findByRole("PM")) {
			logger.info(user.toString());
		}
		
		logger.info("-------------------------------");
		logger.info("Finding all users with role PM or Admin");
		logger.info("-------------------------------");
		for(User user: userRepository.findAllByRole()) {
			logger.info(user.toString()); 
		}
		
		logger.info("-------------------------------");
		logger.info("Finding all users using collection parameter");
		logger.info("-------------------------------");
		List<String> roles = new ArrayList<String>();
		roles.add("Editor");
		roles.add("Admin");
		for(User user: userRepository.findAllByRole(roles)) {
			logger.info(user.toString()); 
		}
		
		logger.info("-------------------------------");
		logger.info("Finding all users by roles IN");
		logger.info("-------------------------------");
		roles = new ArrayList<String>();
		roles.add("Editor");
		roles.add("PM");
		for(User user: userRepository.findByRoleIn(roles)) {
			logger.info(user.toString()); 
		}
	}
	
}
