package com.amdocs.assignment.model;

public class Update {
	
	private String username;
	private String password;
	
	
	private String newUsername;
	private String newPassword;
	private String newName;
	private String newAddress;
	private Integer newPhoneNo;
	
	
	
	public Update(String username, String password, String newUsername, String newPassword, String newName,
			String newAddress, Integer newPhoneNo) {
		super();
		this.username = username;
		this.password = password;
		this.newUsername = newUsername;
		this.newPassword = newPassword;
		this.newName = newName;
		this.newAddress = newAddress;
		this.newPhoneNo = newPhoneNo;
	}
	public String getNewAddress() {
		return newAddress;
	}
	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}
	public Integer getNewPhoneNo() {
		return newPhoneNo;
	}
	public void setNewPhoneNo(Integer newPhoneNo) {
		this.newPhoneNo = newPhoneNo;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewUsername() {
		return newUsername;
	}
	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	
	
}
