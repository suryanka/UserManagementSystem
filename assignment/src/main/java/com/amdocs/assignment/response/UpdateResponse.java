package com.amdocs.assignment.response;

import com.amdocs.assignment.model.User;

public class UpdateResponse {
	
	private User user;
	private String message;
	
	public UpdateResponse(User user, String message) {
		super();
		this.user = user;
		this.message = message;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
