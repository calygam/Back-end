package com.calygam.back.projections;

import java.time.LocalDate;

import com.calygam.back.enums.StatusOfLife;

public interface ProgressAssignProjection {
	Long getProgressId();
    Long getUserId();
    Long getTrailId();
    Long getActivityId();
    StatusOfLife getTrailStatus();
    StatusOfLife getActivityStatus();
    LocalDate getCreatedAt();
    LocalDate getUpdatedAt();
    Long getUnlockedActivities();
    String getArchiveName();
	
}
