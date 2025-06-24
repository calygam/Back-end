package com.calygam.back.dtos;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.models.ActivityProgressEntity;

public class ProgressSubmitActivityDTO {
	
	private MultipartFile activityFile;
	private String activitySubmitedFile;


	public ProgressSubmitActivityDTO() {
		super();
	}

	
	public ProgressSubmitActivityDTO(MultipartFile activityFile, String activitySubmitedFile) {
		super();
		this.activityFile = activityFile;
		this.activitySubmitedFile = activitySubmitedFile;
	}
	
	public ProgressSubmitActivityDTO (ActivityProgressEntity entity) {
		 this.activitySubmitedFile = ServletUriComponentsBuilder
	                .fromCurrentContextPath()
	                .path("/file/read/")         
	                .path(entity.getArchiveName())
	                .toUriString();
	}
	


	public MultipartFile getActivityFile() {
		return activityFile;
	}
	
	public void setActivityFile(MultipartFile activityFile) {
		this.activityFile = activityFile;
	}


	public String getActivitySubmitedFile() {
		return activitySubmitedFile;
	}


	public void setActivitySubmitedFile(String activitySubmitedFile) {
		this.activitySubmitedFile = activitySubmitedFile;
	}
	
	

	
}

