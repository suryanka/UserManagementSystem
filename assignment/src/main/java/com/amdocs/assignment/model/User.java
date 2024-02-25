package com.amdocs.assignment.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name ="_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique= true)
	private String username;
	@Column(nullable = false)
	private String name;
	public User(Integer id, String username, String name, String password, String address, Integer phoneNo) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	
	public User() {
        // Default constructor
    }
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String address;
	
	//@Size(min = 10, max = 10, message = "Phone number must be 10 digits")
	private Integer phoneNo;
   
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	
	public Integer getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
