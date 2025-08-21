package com.calygam.back.models;

import com.calygam.back.utils.GenericFileManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_submission")
public class SubmissionEntity implements GenericFileManagement {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long submissionId;
		
	@Column(name="submission_archive_name")
	private String archiveName;
	
	@Column(name="submission_original_name")
	private String originalName;
	
	@Column(name="submission_archive_path")
	private String archivePath;
	
	@Column(name="submission_archive_type")
	private String archiveType;
	
	@ManyToOne
	@JoinColumn(name="progressId")
	private ActivityProgressEntity progress;



	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}

	public String getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public ActivityProgressEntity getProgress() {
		return progress;
	}

	public void setProgress(ActivityProgressEntity progress) {
		this.progress = progress;
	}
	
	
	
	
}
