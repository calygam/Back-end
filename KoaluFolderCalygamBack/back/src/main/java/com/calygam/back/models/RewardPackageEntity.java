package com.calygam.back.models;

import java.util.ArrayList;
import java.util.List;

import com.calygam.back.enums.DifficultyEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_reward_package")
public class RewardPackageEntity {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reward_package_id")
	private Long rewardPackageId;
	
	@Column(name="reward_activity_difficulty")
	private DifficultyEnum rewardActivityDifficulty;
	
	@Column(name="reward_package_xp")
	private Long rewardPackageXp;
	
	@Column(name="reward_package_money")
	private Long rewardPackageMoney;
	
	@Column(name="reward_package_food")
	private Long rewardPackageFood;
	
	@OneToMany(mappedBy="rewardPackage",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ActivityEntity> activities = new ArrayList<>();

	public RewardPackageEntity() {
		super();
	}

	public RewardPackageEntity(Long rewardPackageId, DifficultyEnum rewardActivityDifficulty, Long rewardPackageXp,
			Long rewardPackageMoney, Long rewardPackageFood, List<ActivityEntity> activities) {
		super();
		this.rewardPackageId = rewardPackageId;
		this.rewardActivityDifficulty = rewardActivityDifficulty;
		this.rewardPackageXp = rewardPackageXp;
		this.rewardPackageMoney = rewardPackageMoney;
		this.rewardPackageFood = rewardPackageFood;
		this.activities = activities;
	}

	public Long getRewardPackageId() {
		return rewardPackageId;
	}

	public void setRewardPackageId(Long rewardPackageId) {
		this.rewardPackageId = rewardPackageId;
	}

	public DifficultyEnum getRewardActivityDifficulty() {
		return rewardActivityDifficulty;
	}

	public void setRewardActivityDifficulty(DifficultyEnum rewardActivityDifficulty) {
		this.rewardActivityDifficulty = rewardActivityDifficulty;
	}

	public Long getRewardPackageXp() {
		return rewardPackageXp;
	}

	public void setRewardPackageXp(Long rewardPackageXp) {
		this.rewardPackageXp = rewardPackageXp;
	}

	public Long getRewardPackageMoney() {
		return rewardPackageMoney;
	}

	public void setRewardPackageMoney(Long rewardPackageMoney) {
		this.rewardPackageMoney = rewardPackageMoney;
	}

	public Long getRewardPackageFood() {
		return rewardPackageFood;
	}

	public void setRewardPackageFood(Long rewardPackageFood) {
		this.rewardPackageFood = rewardPackageFood;
	}

	public List<ActivityEntity> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityEntity> activities) {
		this.activities = activities;
	}
	
	
	
}
