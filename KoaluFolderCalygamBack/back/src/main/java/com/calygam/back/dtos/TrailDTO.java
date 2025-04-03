package com.calygam.back.dtos;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.calygam.back.models.TrailEntity;

import jakarta.persistence.Column;

public class TrailDTO {
	private Long trailId;
	private String trailName;
	private String trailDescription;
	private Long trailPrice;
	private MultipartFile trailFileImage;
	private String trailImage;
	private LocalDate trailCreatedDate;
	private LocalDate trailUpdatedDate;
	private String trailPassword;
	private Long trailVacancy;
	private Long trailVacancies;
	private Long user;

	public TrailDTO() {
		super();
	}

	
	public TrailDTO(String trailName,  Long user) {
		super();
		this.trailName = trailName;
		this.user = user;
	}


	
	
	public TrailDTO(Long trailId, String trailName, String trailDescription, Long trailPrice, MultipartFile trailFileImage,
			LocalDate trailCreatedDate, LocalDate trailUpdatedDate, String trailPassword, Long trailVacancy,
			Long trailVacancies, Long user) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailDescription = trailDescription;
		this.trailPrice = trailPrice;
		this.trailFileImage = trailFileImage;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
		this.trailPassword = trailPassword;
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
	}


	public TrailDTO(Long trailId, String trailName,String trailImage, String trailDescription, Long trailPrice,
			LocalDate trailCreatedDate, LocalDate trailUpdatedDate, Long trailVacancy,
			Long trailVacancies, Long user) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailImage = trailImage;
		this.trailDescription = trailDescription;
		this.trailPrice = trailPrice;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
	
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
	}





	public TrailDTO(TrailEntity entity) {
		super();
		trailId = entity.getTrailId();
		trailName = entity.getTrailName();
		trailImage = entity.getTrailImage();
		trailDescription = entity.getTrailDescription();
		trailPrice = entity.getTrailPrice();
		trailCreatedDate = entity.getTrailCreatedDate();
		trailUpdatedDate = entity.getTrailUpdatedDate();
		trailPassword = entity.getTrailPassword();
		trailVacancy = entity.getTrailVacancy();
		trailVacancies = entity.getTrailVacancies();
		user = entity.getUser().getUserId();
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


	public Long getTrailVacancy() {
		return trailVacancy;
	}


	public void setTrailVacancy(Long trailVacancy) {
		this.trailVacancy = trailVacancy;
	}


	public Long getTrailVacancies() {
		return trailVacancies;
	}


	public void setTrailVacancies(Long trailVacancies) {
		this.trailVacancies = trailVacancies;
	}





	public Long getUser() {
		return user;
	}





	public void setUser(Long user) {
		this.user = user;
	}


	public MultipartFile getTrailFileImage() {
		return trailFileImage;
	}


	public void setTrailFileImage(MultipartFile trailFileImage) {
		this.trailFileImage = trailFileImage;
	}


	public String getTrailImage() {
		return trailImage;
	}


	public void setTrailImage(String trailImage) {
		this.trailImage = trailImage;
	}
	
	
	
	
	
	
	
	
	
	
}
