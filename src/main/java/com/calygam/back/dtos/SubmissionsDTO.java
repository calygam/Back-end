package com.calygam.back.dtos;

public class SubmissionsDTO {
	  private String submissionArchiveUrl;
	  private String submissionOriginalName;
	  private Long 	submissionId;
	  private String submissionLink;
	public SubmissionsDTO() {
		super();
	}
	public SubmissionsDTO(String submissionArchiveUrl, String submissionOriginalName, Long submissionId) {
		super();
		this.submissionArchiveUrl = submissionArchiveUrl;
		this.submissionOriginalName = submissionOriginalName;
		this.submissionId = submissionId;
	}
	public String getSubmissionArchiveUrl() {
		return submissionArchiveUrl;
	}
	public void setSubmissionArchiveUrl(String submissionArchiveUrl) {
		this.submissionArchiveUrl = submissionArchiveUrl;
	}
	public String getSubmissionOriginalName() {
		return submissionOriginalName;
	}
	public void setSubmissionOriginalName(String submissionOriginalName) {
		this.submissionOriginalName = submissionOriginalName;
	}
	public Long getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}
	public String getSubmissionLink() {
		return submissionLink;
	}
	public void setSubmissionLink(String submissionLink) {
		this.submissionLink = submissionLink;
	}
	  
	  
}
