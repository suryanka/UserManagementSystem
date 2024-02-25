package com.amdocs.assignment.service;

import java.util.List;
import java.util.Map;

import com.amdocs.assignment.model.Login;
import com.amdocs.assignment.model.Update;
import com.amdocs.assignment.model.User;
//import com.amdocs.assignment.model.User;
import com.amdocs.assignment.response.LoginResponse;
import com.amdocs.assignment.response.UpdateResponse;

public interface UserService {

	User createUser(User user);

	UpdateResponse updateUserByUserName(String username, Update update);

	LoginResponse loginUser(Login login);

	List<User> getAllUsers();

	boolean deleteEmployee(String username, Login login);

	

}
