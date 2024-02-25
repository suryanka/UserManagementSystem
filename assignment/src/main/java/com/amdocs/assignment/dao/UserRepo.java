package com.amdocs.assignment.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.assignment.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
