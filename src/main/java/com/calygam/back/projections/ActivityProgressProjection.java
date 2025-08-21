package com.calygam.back.projections;


	public interface ActivityProgressProjection {

	    Long getProgressId();
	    Long getActivityId();
	    Long getTrailId();
	    Integer getActivityStatus();
	    String getActivityName();
	    String getActivityDescription();
	    String getActivityDifficulty();
	    Long getRewardPackageMoney();
	    Long getRewardPackageXp();
	    Long getRewardPackageFood();
	 
	}

