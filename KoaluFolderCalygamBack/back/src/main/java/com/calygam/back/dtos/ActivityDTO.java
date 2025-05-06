package com.calygam.back.dtos;

import java.time.LocalDate;

import com.calygam.back.enums.DifficultyEnum;
import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.models.ActivityEntity;

import jakarta.persistence.Column;

public class ActivityDTO {

	private Integer activityId;

	private String activityName;

	private String activityDescription;
	

	private Integer activityPrice;
	

	private DifficultyEnum activityDifficulty;
	
	
	private StatusOfLife activityStatus;
	
	
	private LocalDate activityCreatedAt;
	

	private LocalDate activityUpdatedAt;

	public ActivityDTO() {
		super();
	}

	public ActivityDTO(Integer activityId, String activityName, String activityDescription, Integer activityPrice,
			DifficultyEnum activityDifficulty, StatusOfLife activityStatus, LocalDate activityCreatedAt,
			LocalDate activityUpdatedAt) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityDescription = activityDescription;
		this.activityPrice = activityPrice;
		this.activityDifficulty = activityDifficulty;
		this.activityStatus = activityStatus;
		this.activityCreatedAt = activityCreatedAt;
		this.activityUpdatedAt = activityUpdatedAt;
	}
	
	public ActivityDTO(ActivityEntity entity) {
		super();
		activityId = entity.getActivityId();
		activityName = entity.getActivityName();
		activityDescription = entity.getActivityDescription();
		activityPrice = entity.getActivityPrice();
		activityDifficulty = entity.getActivityDifficulty();
		activityStatus = entity.getActivityStatus();
		activityCreatedAt = entity.getActivityCreatedAt();
		activityUpdatedAt = entity.getActivityUpdatedAt();
	}
	
	

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public Integer getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Integer activityPrice) {
		this.activityPrice = activityPrice;
	}

	public DifficultyEnum getActivityDifficulty() {
		return activityDifficulty;
	}

	public void setActivityDifficulty(DifficultyEnum activityDifficulty) {
		this.activityDifficulty = activityDifficulty;
	}

	public StatusOfLife getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(StatusOfLife activityStatus) {
		this.activityStatus = activityStatus;
	}

	public LocalDate getActivityCreatedAt() {
		return activityCreatedAt;
	}

	public void setActivityCreatedAt(LocalDate activityCreatedAt) {
		this.activityCreatedAt = activityCreatedAt;
	}

	public LocalDate getActivityUpdatedAt() {
		return activityUpdatedAt;
	}

	public void setActivityUpdatedAt(LocalDate activityUpdatedAt) {
		this.activityUpdatedAt = activityUpdatedAt;
	}
	
	
}
