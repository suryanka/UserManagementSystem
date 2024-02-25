package com.amdocs.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.assignment.model.Login;
import com.amdocs.assignment.model.Update;
import com.amdocs.assignment.model.User;
import com.amdocs.assignment.response.LoginResponse;
import com.amdocs.assignment.response.UpdateResponse;
import com.amdocs.assignment.service.UserService;

@CrossOrigin(origins  = "http://localhost:9090")
@RestController
@RequestMapping("/assignment")

public class UserController {
	
	@Autowired
	private final UserService userService ;

	// Constructor injection of UserService
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	// Endpoint to create a new user
	@PostMapping(value = "/profile", produces= "application/json")
	public User createUser (@RequestBody User user) {
		return userService.createUser(user);
	}
	
	// Endpoint for user login
	@PostMapping(value = "/login",  produces= "application/json")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody Login login)
	{
		LoginResponse loginresponse = userService.loginUser(login);
		return ResponseEntity.ok(loginresponse);
	}
	
	// Endpoint to update user information by username
	@PutMapping("/profile/{username}")
	public String updateUserbyUsername(@PathVariable String username,
			@RequestBody Update update) {
		//TODO: process PUT request
		UpdateResponse upres= userService.updateUserByUserName(username, update);
		return upres.getMessage();
	}
	
	// Endpoint to retrieve all users
	@GetMapping("/profile")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	// Endpoint to delete a user by username
	@DeleteMapping("/profile/{username}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String username,
			@RequestBody Login login)
	{
		boolean deleted = false;
		deleted = userService.deleteEmployee(username, login);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", deleted);
		return ResponseEntity.ok(response);
		
	}
	
}
