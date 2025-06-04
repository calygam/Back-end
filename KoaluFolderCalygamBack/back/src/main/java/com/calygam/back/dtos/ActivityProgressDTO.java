package com.calygam.back.dtos;

import java.time.LocalDate;

import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.models.ActivityEntity;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.models.UserEntity;


public class ActivityProgressDTO {
	
	private Long progressId;
	private UserEntity user;
	private TrailEntity trail;
	private ActivityEntity activity;
	private String hashPassword;
	
	
	
	
	public ActivityProgressDTO() {
		super();
	}

	public ActivityProgressDTO(Long progressId, UserEntity user, TrailEntity trail, ActivityEntity activity,
			String hashPassword) {
		super();
		this.progressId = progressId;
		this.user = user;
		this.trail = trail;
		this.activity = activity;
		this.hashPassword = hashPassword;
	}

	public Long getProgressId() {
		return progressId;
	}
	public void setProgressId(Long progressId) {
		this.progressId = progressId;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public TrailEntity getTrail() {
		return trail;
	}
	public void setTrail(TrailEntity trail) {
		this.trail = trail;
	}
	public ActivityEntity getActivity() {
		return activity;
	}
	public void setActivity(ActivityEntity activity) {
		this.activity = activity;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	

	
	
	
	
}
