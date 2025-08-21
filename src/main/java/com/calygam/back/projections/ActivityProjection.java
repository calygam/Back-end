package com.calygam.back.projections;

import java.time.LocalDate;

import com.calygam.back.enums.DifficultyEnum;
import com.calygam.back.enums.StatusOfLife;

public interface ActivityProjection {
	Long getActivityId();
	String getActivityName();
	String getActivityDescription();
	
	DifficultyEnum getActivityDifficulty();
	StatusOfLife getActivityStatus();
	LocalDate getActivityCreatedAt();
	LocalDate getActivityUpdatedAt();
	
}
