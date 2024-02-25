package com.amdocs.assignment.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import com.amdocs.assignment.config.SecurityConfig;
import com.amdocs.assignment.model.User;

@DataJpaTest
public class UserRepoTest {
	
	@Autowired
	private UserRepo userRepo;
	User user;
	
	
	
	@BeforeEach
	void setUp()
	{
		user = new User();
		user.setName("Ankit");
		user.setPassword("Dummy Password");
		user.setUsername("Ankit1");
		user.setAddress("Dummy Address");
		user.setPhoneNo( 1234567890);
		userRepo.save(user);
	}
	
	@AfterEach
	void tearDown()
	{
		user=null;
		userRepo.deleteAll();
	}
	//TestCase Success
	
	@Test
	void testFindByUserName_Found()
	{

		User user1 = userRepo.findByUsername("Ankit1");

		assertThat(user1.getId()).isEqualTo(user.getId());
		
		assertThat(user1.getName()).isEqualTo(user.getName());
		assertThat(user1.getUsername()).isEqualTo(user.getUsername());
	}
	
	//TestCase Failure
	@Test
	void testFindByUserName_NotFound()
	{
		User user1= userRepo.findByUsername("Rohan2");
		assertThat(user1 == null).isTrue();
		
	}
}
