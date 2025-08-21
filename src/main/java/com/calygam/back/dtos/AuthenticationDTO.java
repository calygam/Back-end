package com.calygam.back.dtos;

public class AuthenticationDTO {
	private String userEmail;
	private String userPassword;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public AuthenticationDTO(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	
	
}
