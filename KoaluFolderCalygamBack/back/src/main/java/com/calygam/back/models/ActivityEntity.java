package com.calygam.back.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.calygam.back.enums.DifficultyEnum;
import com.calygam.back.enums.StatusOfLife;

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
@Table(name="tb_activities")
public class ActivityEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	@Column(name="activity_id")
	private Integer activityId;
	
	@Column(name="activity_name")
	private String activityName;
	
	@Column(name="activity_description",columnDefinition="TEXT")
	private String activityDescription;
	
	@Column(name="activity_price")
	private Integer activityPrice;
	
	@Column(name="activity_difficulty")
	private DifficultyEnum activityDifficulty;
	
	@Column(name="activity_status")
	private StatusOfLife activityStatus;
	
	@Column(name="activity_created_at")
	private LocalDate activityCreatedAt;
	
	@Column(name="activity_updated_at")
	private LocalDate activityUpdatedAt;
	
	@ManyToOne
	@JoinColumn(name="trail_id")
	private TrailEntity trail;
	
	@OneToMany(mappedBy="activity",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<ActivityProgressEntity> progress = new ArrayList<>();

	public ActivityEntity() {
		super();
	}

	public ActivityEntity(Integer activityId, String activityName, String activityDescription, Integer activityPrice,
			DifficultyEnum activityDifficulty, StatusOfLife activityStatus, LocalDate activityCreatedAt,
			LocalDate activityUpdatedAt, TrailEntity trail) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityDescription = activityDescription;
		this.activityPrice = activityPrice;
		this.activityDifficulty = activityDifficulty;
		this.activityStatus = activityStatus;
		this.activityCreatedAt = activityCreatedAt;
		this.activityUpdatedAt = activityUpdatedAt;
		this.trail = trail;
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

	public TrailEntity getTrail() {
		return trail;
	}

	public void setTrail(TrailEntity trail) {
		this.trail = trail;
	}

	public List<ActivityProgressEntity> getProgress() {
		return progress;
	}

	public void setProgress(List<ActivityProgressEntity> progress) {
		this.progress = progress;
	}
	
	
	
	
	
	
	
	
	
}
