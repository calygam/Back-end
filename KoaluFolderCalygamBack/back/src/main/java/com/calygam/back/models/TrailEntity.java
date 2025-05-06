package com.calygam.back.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.utils.GenericFileManagement;

import jakarta.persistence.CascadeType;
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
public class TrailEntity implements GenericFileManagement {
	
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
	@Column(name="trail_archive_name")
	private String archiveName;
	
	@Column(name="trail_original_name")
	private String originalName;
	
	@Column(name="trail_archive_path")
	private String archivePath;
	
	@Column(name="trail_archive_type")
	private String archiveType;
	
	@OneToMany(mappedBy="trail",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ActivityEntity> activities = new ArrayList<>();
	
	
	public TrailEntity() {
		super();
	}








	public TrailEntity(Long trailId, String trailName, String trailDescription, Long trailPrice,
			LocalDate trailCreatedDate, LocalDate trailUpdatedDate, String trailPassword, StatusOfLife trailStatus,
			Long trailVacancy, Long trailVacancies, UserEntity user, String archiveName, String originalName,
			String archivePath, String archiveType, List<ActivityEntity> activities) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailDescription = trailDescription;
		this.trailPrice = trailPrice;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
		this.trailPassword = trailPassword;
		this.trailStatus = trailStatus;
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
		this.archiveName = archiveName;
		this.originalName = originalName;
		this.archivePath = archivePath;
		this.archiveType = archiveType;
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

	@Override
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
		
	}

	@Override
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
		
	}

	@Override
	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
		
	}

	@Override
	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
		
	}

	@Override
	public String getArchiveName() {
		// TODO Auto-generated method stub
		return archiveName;
	}

	@Override
	public String getOriginalName() {
		// TODO Auto-generated method stub
		return originalName;
	}

	@Override
	public String getArchivePath() {
		// TODO Auto-generated method stub
		return archivePath;
	}

	@Override
	public String getArchiveType() {
		// TODO Auto-generated method stub
		return archiveType;
	}





	
	
}
