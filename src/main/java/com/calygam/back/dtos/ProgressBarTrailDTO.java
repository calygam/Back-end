package com.calygam.back.dtos;

import com.calygam.back.projections.ProgressBarTrailProjection;

public class ProgressBarTrailDTO {
	private Long trailId;
	private Long totalActivitiesCompleted;
	private Long totalActivities;
	public ProgressBarTrailDTO() {
		super();
	}
	
	
	
	public ProgressBarTrailDTO(Long trailId, Long totalActivitiesCompleted, Long totalActivities) {
		
		this.trailId = trailId;
		this.totalActivitiesCompleted = totalActivitiesCompleted;
		this.totalActivities = totalActivities;
	}
	
	public ProgressBarTrailDTO(ProgressBarTrailProjection proj) {
		super();
		this.trailId = proj.getTrailId();
		this.totalActivitiesCompleted = proj.getTotalActivitiesCompleted();
		this.totalActivities = proj.getTotalActivities();
	}



	public Long getTotalActivitiesCompleted() {
		return totalActivitiesCompleted;
	}
	public void setTotalActivitiesCompleted(Long totalActivitiesCompleted) {
		this.totalActivitiesCompleted = totalActivitiesCompleted;
	}
	public Long getTotalActivities() {
		return totalActivities;
	}
	public void setTotalActivities(Long totalActivities) {
		this.totalActivities = totalActivities;
	}



	



	public Long getTrailId() {
		return trailId;
	}



	public void setTrailId(Long trailId) {
		this.trailId = trailId;
	}
	
	
	
}
