package com.calygam.back.projections;

import com.calygam.back.enums.DifficultyEnum;

public interface RewardPackageProjection {
 Long getRewardPackageId();
	 Long getRewardPackageMoney();
	 Long getRewardPackageXp();
	 Long getRewardPackageFood();
	   Integer getRewardActivityDifficulty();

	    default String getRewardActivityDifficultyName() {
	        return DifficultyEnum.values()[getRewardActivityDifficulty()].name();
	    }
}
