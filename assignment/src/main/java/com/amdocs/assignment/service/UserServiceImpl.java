package com.amdocs.assignment.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amdocs.assignment.dao.UserRepo;
import com.amdocs.assignment.model.Login;
import com.amdocs.assignment.model.Update;
import com.amdocs.assignment.model.User;
import com.amdocs.assignment.response.LoginResponse;
import com.amdocs.assignment.response.UpdateResponse;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepository;

	public UserServiceImpl(UserRepo userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User use) {
		// TODO Auto-generated method stub
		User user = new User(use.getId(),use.getUsername(),use.getName(),
				this.passwordEncoder.encode(use.getPassword()),use.getAddress(),use.getPhoneNo());
        
		
		
		// Saving user to repository
		userRepository.save(user);
		return user;
	}

	@Override
	public UpdateResponse updateUserByUserName(String username, Update update) {
		// TODO Auto-generated method stub
		UpdateResponse response = new UpdateResponse(null, "to be set");
		
		// Checking if the provided username matches the username in the update object 
		if (!update.getUsername().equals(username)) {
			response.setUser(null);
			response.setMessage("Can not be updated as usernames mismatch");
			return response;
		} else {
			// Authenticating user based on provided login credentials
			Login login = new Login();
			login.setUsername(update.getUsername());
			login.setPassword(update.getPassword());
			LoginResponse loginResponse = loginUser(login);
			
			// If login is successful, updating user information
			if (loginResponse.getStatus() == true) {
				User use = userRepository.findByUsername(username);
				if(!update.getNewName().equals(""))use.setName(update.getNewName());
				if(!update.getNewUsername().equals(""))use.setUsername(update.getNewUsername());
				if(!update.getNewPassword().equals(""))use.setPassword(this.passwordEncoder.encode(update.getNewPassword()));
				if(!update.getNewAddress().equals(""))use.setAddress(update.getNewAddress());
				if(update.getNewPhoneNo() != null)use.setPhoneNo(update.getNewPhoneNo());
				
				userRepository.save(use);
				response.setUser(use);
				response.setMessage("Updated Successfully");
				return response;
			} else {
				response.setUser(null);
				response.setMessage("Can not Login");
				return response;
			}
		}

	}

	@Override
	public LoginResponse loginUser(Login login) {
		// TODO Auto-generated method stub
		// Retrieving user by username
		User user1 = userRepository.findByUsername(login.getUsername());
		
		// Check if user exists
		if (user1 != null) {
			// Verifying password
			String password = login.getPassword();
			String encodedPassword = user1.getPassword();
			Boolean isPassWordRight = passwordEncoder.matches(password, encodedPassword);
            
			// If password matches, returning successful login response
			if (isPassWordRight) {
				User user = userRepository.findByUsername(login.getUsername());
				
				if (user != null) {
					return new LoginResponse("Login success", true);
				} else {
					return new LoginResponse("Login failed", false);
				}
			} else {
				return new LoginResponse("Password does not match", false);
			}
		} else {
			return new LoginResponse("Username not exists", false);
		}

	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public boolean deleteEmployee(String username, Login login) {
		// TODO Auto-generated method stub
		// Checking if the provided username matches the username in the login object
		if (!login.getUsername().equals(username))
		{
			return false;
		}
		else {
			// Authenticating user based on provided login credentials
			LoginResponse loginResponse = loginUser(login);
			
			// If login is successful, deleting the user
			if(loginResponse.getStatus())
			{
				User user1 = userRepository.findByUsername(username);
				userRepository.delete(user1);
				return true;
			}
			else {
				return false;
			}
		}
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder2) {
		// TODO Auto-generated method stub
		this.passwordEncoder= passwordEncoder2;
		
	}

}
