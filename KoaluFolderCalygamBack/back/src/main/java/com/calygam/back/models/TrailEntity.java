package com.calygam.back.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.calygam.back.enums.StatusOfLife;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_trail")
public class TrailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trail_id")
	private Long trailId;
	
	@Column(name="trail_name")
	private String trailName;
	
	@Column(name="trail_description", columnDefinition = "TEXT")
	private String trailDescription;
	
	@Column(name="trail_price")
	private Long trailPrice;
	
	@Column(name="trail_image")
	private String trailImage;
	
	@Column(name="trail_created_date")
	private LocalDate trailCreatedDate;
	
	@Column(name="trail_updated_date")
	private LocalDate trailUpdatedDate;
	
	@Column(name="trail_password")
	private String trailPassword;
	
	@Column(name="trail_status")
	private StatusOfLife trailStatus;
	
	@Column(name="trail_vacancy")
	private Long trailVacancy;
	
	@Column(name="trail_vacancies")
	private Long trailVacancies;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@OneToMany(mappedBy="trail")
	private List<ActivityEntity> activities = new ArrayList<>();
	
	
	public TrailEntity() {
		super();
	}




	public TrailEntity(Long trailId, String trailName, String trailDescription, Long trailPrice, String trailImage,
			LocalDate trailCreatedDate, LocalDate trailUpdatedDate, String trailPassword, StatusOfLife trailStatus,
			Long trailVacancy, Long trailVacancies, UserEntity user, List<ActivityEntity> activities) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailDescription = trailDescription;
		this.trailPrice = trailPrice;
		this.trailImage = trailImage;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
		this.trailPassword = trailPassword;
		this.trailStatus = trailStatus;
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
		this.activities = activities;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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


	public String getTrailImage() {
		return trailImage;
	}


	public void setTrailImage(String trailImage) {
		this.trailImage = trailImage;
	}

	public List<ActivityEntity> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityEntity> activities) {
		this.activities = activities;
	}




	public StatusOfLife getTrailStatus() {
		return trailStatus;
	}




	public void setTrailStatus(StatusOfLife trailStatus) {
		this.trailStatus = trailStatus;
	}
	
	
}
