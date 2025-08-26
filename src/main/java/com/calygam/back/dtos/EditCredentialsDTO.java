package com.calygam.back.dtos;

import org.springframework.web.multipart.MultipartFile;



public class EditCredentialsDTO {
	private Long id;
	
    private String userName;

 
    private String userEmail;

    private String userPassword;
    private String userNewPassword;
    
    private MultipartFile userMultipartFile;
    

    

	


	

	public EditCredentialsDTO(Long id, String userName, String userEmail, String userPassword, String userNewPassword,
			MultipartFile userMultipartFile) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userNewPassword = userNewPassword;
		this.userMultipartFile = userMultipartFile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public MultipartFile getUserMultipartFile() {
		return userMultipartFile;
	}

	public void setUserMultipartFile(MultipartFile userMultipartFile) {
		this.userMultipartFile = userMultipartFile;
	}

	public String getUserNewPassword() {
		return userNewPassword;
	}

	public void setUserNewPassword(String userNewPassword) {
		this.userNewPassword = userNewPassword;
	}
	
	
	
	
    
    
}
