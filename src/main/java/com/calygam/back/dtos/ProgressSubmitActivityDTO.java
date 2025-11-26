package com.calygam.back.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.models.SubmissionEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ProgressSubmitActivityDTO {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long submissionId;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<MultipartFile> activityFiles;
	
	private String activitySubmitedFile;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String activityOriginalFileName;
	
	private List<String> activityLinks;
	
	private String activityLink;
	


	public ProgressSubmitActivityDTO() {
		super();
	}

	
	
	
	public ProgressSubmitActivityDTO(List<MultipartFile> activityFiles, String activitySubmitedFile,List<String> activityLinks){
		super();
		this.activityFiles = activityFiles;
		this.activitySubmitedFile = activitySubmitedFile;
		this.activityLinks = activityLinks;
	}




	public ProgressSubmitActivityDTO (SubmissionEntity entity) {
		 this.activitySubmitedFile = ServletUriComponentsBuilder
	                .fromCurrentContextPath()
	                .path("/file/read/")         
	                .path(entity.getArchiveName())
	                .toUriString();
	}
	









	public List<String> getActivityLinks() {
		return activityLinks;
	}




	public void setActivityLinks(List<String> activityLinks) {
		this.activityLinks = activityLinks;
	}




	public List<MultipartFile> getActivityFiles() {
		return activityFiles;
	}




	public void setActivityFiles(List<MultipartFile> activityFiles) {
		this.activityFiles = activityFiles;
	}




	public String getActivitySubmitedFile() {
		return activitySubmitedFile;
	}


	public void setActivitySubmitedFile(String activitySubmitedFile) {
		this.activitySubmitedFile = activitySubmitedFile;
	}




	public String getActivityOriginalFileName() {
		return activityOriginalFileName;
	}




	public void setActivityOriginalFileName(String activityOriginalFileName) {
		this.activityOriginalFileName = activityOriginalFileName;
	}




	public Long getSubmissionId() {
		return submissionId;
	}




	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}




	public String getActivityLink() {
		return activityLink;
	}




	public void setActivityLink(String activityLink) {
		this.activityLink = activityLink;
	}
	
	
	
	
	
	

	
}

