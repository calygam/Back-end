package com.calygam.back.dtos;

import java.util.List;

public class SubmittedActivityUserDTO {
	  private String userName;
	  private Long userId;
	  private String userArchiveUrl;
	  private List<SubmissionsDTO> submissions;
	public SubmittedActivityUserDTO() {
		super();
	}


	
	public SubmittedActivityUserDTO(String userName, Long userId, String userArchiveUrl,
			List<SubmissionsDTO> submissions) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.userArchiveUrl = userArchiveUrl;
		this.submissions = submissions;
	}



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserArchiveUrl() {
		return userArchiveUrl;
	}
	public void setUserArchiveUrl(String userArchiveUrl) {
		this.userArchiveUrl = userArchiveUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public List<SubmissionsDTO> getSubmissions() {
		return submissions;
	}



	public void setSubmissions(List<SubmissionsDTO> submissions) {
		this.submissions = submissions;
	}
	
	

	  
	  
}
