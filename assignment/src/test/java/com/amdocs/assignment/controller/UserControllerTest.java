package com.amdocs.assignment.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



import com.amdocs.assignment.model.Login;
import com.amdocs.assignment.model.Update;
import com.amdocs.assignment.model.User;
import com.amdocs.assignment.response.LoginResponse;
import com.amdocs.assignment.response.UpdateResponse;
import com.amdocs.assignment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	
	// Sample user data for testing
	User user1 = new User();
	User user2 = new User();
	Login login= new Login();
	
	List<User> userList = new ArrayList<>();
	
	@BeforeEach
	void setUp() 
	{
		// Initialize sample user data
		user1.setId(1);
		user1.setName("Ankit");
		user1.setPassword("DummyPassword");
		user1.setUsername("Ankit1");
		
		
		user2.setId(2);
		user2.setName("Rohan");
		user2.setPassword("DummyPassword2");
		user2.setUsername("Rohan2");
		
		// Add users to the list
		userList.add(user1);
		userList.add(user2);
		
		// Initialize sample login data
		login.setUsername(user1.getUsername());
		login.setPassword(user1.getPassword());
		
		
		
	}
	
	@AfterEach
	void tearDown()
	{
		// Clean up resources
		user1=null;
		user2=null;
		userList=null;
		login=null;
	}
	
	@Test
	void TestGetAllUsers() throws Exception
	{
		// Mocking the userService to return userList
		when(userService.getAllUsers()).thenReturn(userList);
		
		// Performing GET request and validating response
		this.mockMvc.perform(get("/assignment/profile"))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void TestCreateUser() throws Exception
	{   
		// Mapping user1 object to JSON
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON= mapper.writeValueAsString(user1);
		
		// Mocking userService to return user1
		when(userService.createUser(user1))
		.thenReturn(user1);
		
		
		// Performing POST request and validating response
		 this.mockMvc.perform(post("/assignment/profile")
				 .contentType(MediaType.APPLICATION_JSON).content(requestJSON))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void TestLoginUser() throws Exception
	{
		// Mapping login object to JSON
		ObjectMapper mapper = new ObjectMapper();
		String LoginRequestJSON = mapper.writeValueAsString(login);
		
		// Mocking userService to return successful login response
		when(userService.loginUser(login))
		.thenReturn(new LoginResponse("Login success", true));
		
		// Performing POST request for login and validating response
		this.mockMvc.perform(post("/assignment/login")
				 .contentType(MediaType.APPLICATION_JSON).content(LoginRequestJSON))
		.andDo(print()).andExpect(status().isOk());
	}
	
	
	@Test
	void TestUpdateUserbyUsername() throws Exception
	{
		
		// Initializing update object
		Update update = new Update(user1.getUsername(),user1.getPassword(),"Rohit3","DummyPassword3","Rohit","Rohit",15589999);
		
		update.setUsername(user1.getUsername());
		update.setPassword(user1.getPassword());
		update.setNewName("Rohit");
		update.setNewPassword("DummyPassword3");
		update.setNewUsername("Rohit3");
		
		// Mapping update object to JSON
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(update);
		
		// Mocking userService to return UpdateResponse
		doReturn(new UpdateResponse(user1, "Updated Successfully")).when(userService)
		.updateUserByUserName(Mockito.anyString(), Mockito.any());
		
		 // Performing PUT request for updating user and validating response
		this.mockMvc.perform(put("/assignment/profile/Ankit1")
				 .contentType(MediaType.APPLICATION_JSON).content(requestJSON))
		.andDo(print()).andExpect(status().isOk());
		
	}
	
	@Test
	void TestDeleteEmployee() throws Exception
	{
		// Mapping login object to JSON
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(login);
		
		// Mocking userService to return true for user deletion
		when(userService.deleteEmployee(user1.getUsername(), login))
		.thenReturn(true);
		
		// Performing DELETE request for deleting user and validating response
		this.mockMvc.perform(delete("/assignment/profile/Ankit1")
				 .contentType(MediaType.APPLICATION_JSON).content(requestJSON))
		.andDo(print()).andExpect(status().isOk());
	}
}
