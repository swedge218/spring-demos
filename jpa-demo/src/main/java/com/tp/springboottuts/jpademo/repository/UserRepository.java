package com.tp.springboottuts.jpademo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tp.springboottuts.jpademo.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByRole(String role);
	
	@Query("SELECT u FROM User u WHERE role IN ('PM', 'Admin')")
	List<User> findAllByRole();
	
	@Query("SELECT u FROM User u WHERE role IN :roles")
	List<User> findAllByRole(Collection<String> roles);
	
	List<User> findByRoleIn(Collection<String> roles);
}