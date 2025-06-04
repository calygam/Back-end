package com.calygam.back.dtos;

import java.time.LocalDate;

import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.models.ActivityEntity;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.models.UserEntity;


public class ActivityProgressResponseDTO {
	
	private Long progressId;
    private Long userId;
    private Long trailId;
    private Long activityId;
    private StatusOfLife trailStatus;
    private StatusOfLife activityStatus;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Long unlockedActivities;
	
	
	
	
	public ActivityProgressResponseDTO() {
		super();
	}




	public ActivityProgressResponseDTO(Long progressId, Long userId, Long trailId, Long activityId,
			StatusOfLife trailStatus, StatusOfLife activityStatus, LocalDate createdAt, LocalDate updatedAt,
			Long unlockedActivities) {
		super();
		this.progressId = progressId;
		this.userId = userId;
		this.trailId = trailId;
		this.activityId = activityId;
		this.trailStatus = trailStatus;
		this.activityStatus = activityStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.unlockedActivities = unlockedActivities;
	}




	public Long getProgressId() {
		return progressId;
	}




	public void setProgressId(Long progressId) {
		this.progressId = progressId;
	}




	public Long getUserId() {
		return userId;
	}




	public void setUserId(Long userId) {
		this.userId = userId;
	}




	public Long getTrailId() {
		return trailId;
	}




	public void setTrailId(Long trailId) {
		this.trailId = trailId;
	}




	public Long getActivityId() {
		return activityId;
	}




	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}




	public StatusOfLife getTrailStatus() {
		return trailStatus;
	}




	public void setTrailStatus(StatusOfLife trailStatus) {
		this.trailStatus = trailStatus;
	}




	public StatusOfLife getActivityStatus() {
		return activityStatus;
	}




	public void setActivityStatus(StatusOfLife activityStatus) {
		this.activityStatus = activityStatus;
	}




	public LocalDate getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}




	public LocalDate getUpdatedAt() {
		return updatedAt;
	}




	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}




	public Long getUnlockedActivities() {
		return unlockedActivities;
	}




	public void setUnlockedActivities(Long unlockedActivities) {
		this.unlockedActivities = unlockedActivities;
	}
	
	
	
	
	
}
