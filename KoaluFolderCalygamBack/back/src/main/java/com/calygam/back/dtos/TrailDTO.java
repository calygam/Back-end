package com.calygam.back.dtos;

import java.time.LocalDate;

import com.calygam.back.models.TrailEntity;

import jakarta.persistence.Column;

public class TrailDTO {
	
	private Long trailId;

	private String trailName;

	private String trailDescription;

	private Long trailPrice;

	private LocalDate trailCreatedDate;

	private LocalDate trailUpdatedDate;
	
	private String trailPassword;

	public TrailDTO() {
		super();
	}

	public TrailDTO(Long trailId, String trailName, String trailDescription, Long trailPrice,
			LocalDate trailCreatedDate, LocalDate trailUpdatedDate, String trailPassword) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailDescription = trailDescription;
		this.trailPrice = trailPrice;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
		this.trailPassword = trailPassword;
	}
	
	public TrailDTO(TrailEntity entity) {
		super();
		trailId = entity.getTrailId();
		trailName = entity.getTrailName();
		trailDescription = entity.getTrailDescription();
		trailPrice = entity.getTrailPrice();
		trailCreatedDate = entity.getTrailCreatedDate();
		trailUpdatedDate = entity.getTrailUpdatedDate();
		trailPassword = entity.getTrailPassword();
		
	}

	public Long getTrailId() {
		return trailId;
	}

	public void setTrailId(Long trailId) {
		this.trailId = trailId;
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public String getTrailDescription() {
		return trailDescription;
	}

	public void setTrailDescription(String trailDescription) {
		this.trailDescription = trailDescription;
	}

	public Long getTrailPrice() {
		return trailPrice;
	}

	public void setTrailPrice(Long trailPrice) {
		this.trailPrice = trailPrice;
	}

	public LocalDate getTrailCreatedDate() {
		return trailCreatedDate;
	}

	public void setTrailCreatedDate(LocalDate trailCreatedDate) {
		this.trailCreatedDate = trailCreatedDate;
	}

	public LocalDate getTrailUpdatedDate() {
		return trailUpdatedDate;
	}

	public void setTrailUpdatedDate(LocalDate trailUpdatedDate) {
		this.trailUpdatedDate = trailUpdatedDate;
	}

	public String getTrailPassword() {
		return trailPassword;
	}

	public void setTrailPassword(String trailPassword) {
		this.trailPassword = trailPassword;
	}
	
	
	
	
	
	
}
