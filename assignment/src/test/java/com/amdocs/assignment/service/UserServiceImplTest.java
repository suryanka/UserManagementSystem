package com.amdocs.assignment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amdocs.assignment.dao.UserRepo;
import com.amdocs.assignment.model.Login;
import com.amdocs.assignment.model.Update;
import com.amdocs.assignment.model.User;
import com.amdocs.assignment.response.LoginResponse;
import com.amdocs.assignment.response.UpdateResponse;

public class UserServiceImplTest {

	@Mock
	public UserRepo userRepo;
	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private UserService userService;
	AutoCloseable autoCloseable;
	User user;

	@BeforeEach
	void setUp() {
		// Opening Mockito annotations for this test class
		autoCloseable = MockitoAnnotations.openMocks(this);

		// Creating a spy for UserServiceImpl
		userService = Mockito.spy(new UserServiceImpl(userRepo));

		// Injecting the mock PasswordEncoder into UserServiceImpl
		((UserServiceImpl) userService).setPasswordEncoder(passwordEncoder);

		// Create a sample user
		user = new User();
		user.setId(1);
		user.setName("Ankit");
		user.setPassword("DummyPassword");
		user.setUsername("Ankit1");
	}

	@AfterEach
	void tearDown() throws Exception {
		// Closing Mockito annotations after each test
		autoCloseable.close();
	}

	@Test
	void testCreateUser() {
		// Mocking the behavior of userRepo.save()
		when(userRepo.save(user)).thenReturn(user);
		// Mocking the behavior of passwordEncoder.encode()
		when(passwordEncoder.encode(user.getPassword())).thenReturn("DummyPassword");

		// Calling the method under test
		User createdUser = userService.createUser(user);

		// Asserting the result
		assertThat(createdUser.getId()).isEqualTo(user.getId());
		assertThat(createdUser.getUsername()).isEqualTo(user.getUsername());
		assertThat(createdUser.getName()).isEqualTo(user.getName());
		assertThat(createdUser.getPassword()).isEqualTo(user.getPassword());

	}

	@Test
	void updateUserByUserNameTest() {

		// Mock the behavior of loginUser method to return a successful login response
		doReturn(new LoginResponse("Login success", true)).when(userService).loginUser(Mockito.notNull());

		// Creating an update object with new values
		Update update = new Update(user.getUsername(),"DummyPassword","NewName","Ankit1New",
				"NewPassword","NewAddress", 00000000);

		// Mocking the behavior of userRepository.findByUsername

		when(userRepo.findByUsername(user.getUsername())).thenReturn(user);

		// Mocking the behavior of passwordEncoder.encode
		when(passwordEncoder.encode(update.getNewPassword())).thenReturn("NewPassword");

		// Calling the updateUserByUserName method
		UpdateResponse response = userService.updateUserByUserName(user.getUsername(), update);

		// Asserting the response
		assertThat(response.getMessage()).isEqualTo("Updated Successfully");

	}

	@Test
	void LoginUserTest() {

		Login login = new Login();
		login.setUsername(user.getUsername());
		login.setPassword("DummyPassword");

		// Mocking the behavior of userRepository.findByUsername
		when(userRepo.findByUsername(user.getUsername())).thenReturn(user);

		// Mocking the behaviour of passwordencoder.matches method
		when(passwordEncoder.matches(login.getPassword(), user.getPassword())).thenReturn(true);

		LoginResponse loginResponse = userService.loginUser(login);
		assertThat(loginResponse.getStatus()).isEqualTo(true);

	}

	@Test
	void getAllUsersTest() {
		// Mocking the behavior of userRepo.findAll()
		when(userRepo.findAll()).thenReturn(new ArrayList<User>(Collections.singleton(user)));

		// Asserting the result
		assertThat(userService.getAllUsers().get(0).getUsername()).isEqualTo(user.getUsername());
		assertThat(userService.getAllUsers().get(0).getId()).isEqualTo(user.getId());
		assertThat(userService.getAllUsers().get(0).getName()).isEqualTo(user.getName());
	}

	@Test
	void deleteEmployeeTest() {

		// Creating a sample login Object
		Login login = new Login();
		login.setUsername(user.getUsername());
		login.setPassword("DummyPassword");

		// Mocking the behavior of userRepository.findByUsername
		when(userRepo.findByUsername(user.getUsername())).thenReturn(user);

		// Mocking the behavior of password.encoder
		when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

		// Mocking the behavior of loginUser method to return a successful login response
		LoginResponse loginResponse = new LoginResponse("Login success", true);

		doReturn(new LoginResponse("Login success", true)).when(userService).loginUser(Mockito.notNull());

		doAnswer(Answers.CALLS_REAL_METHODS).when(userService).deleteEmployee(user.getUsername(), login);

		// Asserting the result
		assertThat(userService.deleteEmployee(user.getUsername(), login)).isEqualTo(true);
	}

}
