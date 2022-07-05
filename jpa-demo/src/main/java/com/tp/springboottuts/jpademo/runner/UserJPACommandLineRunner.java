package com.tp.springboottuts.jpademo.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tp.springboottuts.jpademo.model.User;
import com.tp.springboottuts.jpademo.repository.UserJPARepository;

@Component
public class UserJPACommandLineRunner implements CommandLineRunner {

	@Autowired
	UserJPARepository userJPARepository;

	public static final Logger logger = LoggerFactory.getLogger(UserJPACommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		userJPARepository.save(new User("LekeJ", "Admin"));
		userJPARepository.save(new User("TemiJ", "Editor"));
		userJPARepository.save(new User("AkinJ", "PM"));

		User userRoto = new User("RotoJ", "PM");
		userJPARepository.save(userRoto);

		logger.info("-------------------------------");
		logger.info("Finding all users");
		logger.info("-------------------------------");
		for (User user : userJPARepository.findAll()) {
			logger.info(user.toString());
		}

		logger.info("-------------------------------");
		logger.info("Finding first user by ID");
		logger.info("-------------------------------");
		Optional<User> userById = userJPARepository.findById(1L);
		logger.info(userById.isEmpty() ? "Found ID: " + userById.toString() : "");

		logger.info("-------------------------------");
		logger.info("Finding all users with role PM");
		logger.info("-------------------------------");
		for (User user : userJPARepository.findByRole("PM")) {
			logger.info(user.toString());
		}

		logger.info("-------------------------------");
		logger.info("Finding all users with role PM or Admin");
		logger.info("-------------------------------");
		for (User user : userJPARepository.findAllByRole()) {
			logger.info(user.toString());
		}

		logger.info("-------------------------------");
		logger.info("Finding all users using collection parameter");
		logger.info("-------------------------------");
		List<String> roles = new ArrayList<String>();
		roles.add("Editor");
		roles.add("Admin");
		for (User user : userJPARepository.findAllByRole(roles)) {
			logger.info(user.toString());
		}

		logger.info("-------------------------------");
		logger.info("Finding all users by roles IN");
		logger.info("-------------------------------");
		roles = new ArrayList<String>();
		roles.add("Editor");
		roles.add("PM");
		for (User user : userJPARepository.findByRoleIn(roles)) {
			logger.info(user.toString());
		}

		logger.info("-------------------------------");
		logger.info("Updating User Roto, changing role to Writer");
		logger.info("-------------------------------");
		userRoto.setRole("Writer");
		userJPARepository.save(userRoto);

		logger.info("-------------------------------");
		logger.info("Deleting user with ID 2");
		logger.info("-------------------------------");
		userJPARepository.deleteById(2L);

		logger.info("-------------------------------");
		logger.info("Finding all users");
		logger.info("-------------------------------");
		for (User user : userJPARepository.findAll()) {
			logger.info(user.toString());
		}
	}

}
