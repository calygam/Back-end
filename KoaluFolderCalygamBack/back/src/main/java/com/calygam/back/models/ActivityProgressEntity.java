package com.calygam.back.models;

import java.time.LocalDate;

import com.calygam.back.enums.StatusOfLife;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_trail_x_activity_progress")
public class ActivityProgressEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long progressId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="trail_id")
	private TrailEntity trail;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private ActivityEntity activity;
	
	@Column(name="trail_status")
	private StatusOfLife trailStatus;
	
	@Column(name="activity_status")
	private StatusOfLife activityStatus;
	
	@Column(name="created_at")
	private LocalDate createdAt;
	@Column(name="updated_at")
	private LocalDate updatedAt;
	
	@Column(name="unlocked_activities")
	private Long unlockedActivities;
	
	

	public ActivityProgressEntity() {
		super();
	}







	public ActivityProgressEntity(Long progressId, UserEntity user, TrailEntity trail, ActivityEntity activity,
			StatusOfLife trailStatus, StatusOfLife activityStatus, LocalDate createdAt, LocalDate updatedAt,
			Long unlockedActivities) {
		super();
		this.progressId = progressId;
		this.user = user;
		this.trail = trail;
		this.activity = activity;
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
