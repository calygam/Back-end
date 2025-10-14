package com.calygam.back.dtos;

public class SecurePasswordDTO {
	
	private String randomSecurePassword;
	
	

	public SecurePasswordDTO() {
		super();
	}

	public SecurePasswordDTO(String randomSecurePassword) {
		super();
		this.randomSecurePassword = randomSecurePassword;
	}

	public String getRandomSecurePassword() {
		return randomSecurePassword;
	}

	public void setRandomSecurePassword(String randomSecurePassword) {
		this.randomSecurePassword = randomSecurePassword;
	}
	
	
}
