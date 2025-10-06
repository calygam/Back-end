package com.calygam.back.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.models.TrailEntity;

public class TrailDTO {
	private Long trailId;
	private String trailName;
	private String trailDescription;
	private StatusOfLife trailStatus;

	private MultipartFile trailFileImage;
	private String trailImage;
	private LocalDate trailCreatedDate;
	private LocalDate trailUpdatedDate;
	private String trailPassword;
	private Long trailVacancy;
	private Long trailVacancies;
	private Long user;
	private Boolean trailHavePassword;
	
	private List<ActivityDTO> activities = new ArrayList<>();
	private String calygamCode;
	
	private ProgressBarTrailDTO progressBarTrailDTO;


	public TrailDTO() {
		super();
	}

	
	public TrailDTO(String trailName,  Long user) {
		super();
		this.trailName = trailName;
		this.user = user;
	}


	
	
	public TrailDTO(Long trailId, String trailName, String trailDescription,  MultipartFile trailFileImage,
			LocalDate trailCreatedDate, LocalDate trailUpdatedDate, String trailPassword, Long trailVacancy,
			Long trailVacancies, Long user) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailDescription = trailDescription;
		
		this.trailFileImage = trailFileImage;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
		this.trailPassword = trailPassword;
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
	}
	
	
	
	


	public TrailDTO(Long trailId, String trailName, String trailDescription,
			MultipartFile trailFileImage, String trailImage, LocalDate trailCreatedDate, LocalDate trailUpdatedDate,
			String trailPassword, Long trailVacancy, Long trailVacancies, Long user, List<ActivityDTO> activities) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailDescription = trailDescription;
	
		this.trailFileImage = trailFileImage;
		this.trailImage = trailImage;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
		this.trailPassword = trailPassword;
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
		this.activities = activities;
	}


	public TrailDTO(Long trailId, String trailName,String trailImage, String trailDescription,
			LocalDate trailCreatedDate, LocalDate trailUpdatedDate, Long trailVacancy,
			Long trailVacancies, Long user) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailImage = trailImage;
		this.trailDescription = trailDescription;
	
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
	
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
	}
	
	





	public TrailDTO(Long trailId, String trailName, String trailDescription, StatusOfLife trailStatus,
			String trailImage, LocalDate trailCreatedDate, LocalDate trailUpdatedDate, String trailPassword,
			Long trailVacancy, Long trailVacancies, Long user, List<ActivityDTO> activities, String calygamCode,
			ProgressBarTrailDTO progressBarTrailDTO) {
		super();
		this.trailId = trailId;
		this.trailName = trailName;
		this.trailDescription = trailDescription;
		this.trailStatus = trailStatus;
		this.trailImage = trailImage;
		this.trailCreatedDate = trailCreatedDate;
		this.trailUpdatedDate = trailUpdatedDate;
		this.trailPassword = trailPassword;
		this.trailVacancy = trailVacancy;
		this.trailVacancies = trailVacancies;
		this.user = user;
		this.activities = activities;
		this.calygamCode = calygamCode;
		this.progressBarTrailDTO = progressBarTrailDTO;
	}


	public TrailDTO(TrailEntity entity) {
		super();
		trailId = entity.getTrailId();
		trailName = entity.getTrailName();
		trailImage = entity.getArchiveName();
		trailDescription = entity.getTrailDescription();
		trailStatus = entity.getTrailStatus();
	
		trailCreatedDate = entity.getTrailCreatedDate();
		trailUpdatedDate = entity.getTrailUpdatedDate();
		//
		trailVacancy = entity.getTrailVacancy();
		trailVacancies = entity.getTrailVacancies();
		user = entity.getUser().getUserId();
		 this.trailImage = ServletUriComponentsBuilder
	                .fromCurrentContextPath()
	                .path("/file/read/")         
	                .path(entity.getArchiveName())
	                .toUriString();
		this.activities = entity.getActivities().stream()
		        .map(ActivityDTO::new) 
		        .collect(Collectors.toList());
		this.trailHavePassword =trailPassword==null || trailPassword==""? false:true;
		
	}
	//.s
	public TrailDTO(TrailEntity entity,ProgressBarTrailDTO prog) {
		super();
		trailId = entity.getTrailId();
		trailName = entity.getTrailName();
		trailImage = entity.getArchiveName();
		trailDescription = entity.getTrailDescription();
		trailStatus = entity.getTrailStatus();
	
		trailCreatedDate = entity.getTrailCreatedDate();
		trailUpdatedDate = entity.getTrailUpdatedDate();
		
		trailVacancy = entity.getTrailVacancy();
		trailVacancies = entity.getTrailVacancies();
		user = entity.getUser().getUserId();
		 this.trailImage = ServletUriComponentsBuilder
	                .fromCurrentContextPath()
	                .path("/file/read/")         
	                .path(entity.getArchiveName())
	                .toUriString();
		this.activities = entity.getActivities().stream()
		        .map(ActivityDTO::new) 
		        .collect(Collectors.toList());
		this.progressBarTrailDTO = prog;
		
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


	public List<ActivityDTO> getActivities() {
		return activities;
	}


	public void setActivities(List<ActivityDTO> activities) {
		this.activities = activities;
	}


	public String getCalygamCode() {
		return calygamCode;
	}


	public void setCalygamCode(String calygamCode) {
		this.calygamCode = calygamCode;
	}


	public StatusOfLife getTrailStatus() {
		return trailStatus;
	}


	public void setTrailStatus(StatusOfLife trailStatus) {
		this.trailStatus = trailStatus;
	}


	public ProgressBarTrailDTO getProgressBarTrailDTO() {
		return progressBarTrailDTO;
	}


	public void setProgressBarTrailDTO(ProgressBarTrailDTO progressBarTrailDTO) {
		this.progressBarTrailDTO = progressBarTrailDTO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
