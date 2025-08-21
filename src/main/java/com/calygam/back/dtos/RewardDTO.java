package com.calygam.back.dtos;

import com.calygam.back.enums.DifficultyEnum;

public class RewardDTO {
	
	private Long rewardPackageId;
	private Long rewardPackageMoney;
	private Long rewardPackageXp;
	private Long rewardPackageFood;
	private DifficultyEnum rewardPackageDifficulty;
	public RewardDTO() {
		super();
	}

	public RewardDTO(Long rewardPackageId, Long rewardPackageMoney, Long rewardPackageXp, Long rewardPackageFood,
			DifficultyEnum rewardPackageDifficulty) {
		super();
		this.rewardPackageId = rewardPackageId;
		this.rewardPackageMoney = rewardPackageMoney;
		this.rewardPackageXp = rewardPackageXp;
		this.rewardPackageFood = rewardPackageFood;
		this.rewardPackageDifficulty = rewardPackageDifficulty;
	}

	public Long getRewardPackageId() {
		return rewardPackageId;
	}
	public void setRewardPackageId(Long rewardPackageId) {
		this.rewardPackageId = rewardPackageId;
	}
	public Long getRewardPackageMoney() {
		return rewardPackageMoney;
	}
	public void setRewardPackageMoney(Long rewardPackageMoney) {
		this.rewardPackageMoney = rewardPackageMoney;
	}
	public Long getRewardPackageXp() {
		return rewardPackageXp;
	}
	public void setRewardPackageXp(Long rewardPackageXp) {
		this.rewardPackageXp = rewardPackageXp;
	}
	public Long getRewardPackageFood() {
		return rewardPackageFood;
	}
	public void setRewardPackageFood(Long rewardPackageFood) {
		this.rewardPackageFood = rewardPackageFood;
	}

	public DifficultyEnum getRewardPackageDifficulty() {
		return rewardPackageDifficulty;
	}

	public void setRewardPackageDifficulty(DifficultyEnum rewardPackageDifficulty) {
		this.rewardPackageDifficulty = rewardPackageDifficulty;
	}
	
	
}
