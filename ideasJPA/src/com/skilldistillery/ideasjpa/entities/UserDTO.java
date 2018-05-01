package com.skilldistillery.ideasjpa.entities;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserDTO {
	
	@Size(min = 6, max = 45)
	private String username;

	@Size(min = 6, max = 45)
	private String password;

	private String confirmPassword;
	@Email
	private String email;
	
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
