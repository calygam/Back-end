package com.calygam.back.utils;

import org.springframework.stereotype.Component;

import com.calygam.back.models.ApprenticeInventoryEntity;
import com.calygam.back.models.PetEntity;
import com.calygam.back.models.PetOutfitEntity;
import com.calygam.back.models.RewardPackageEntity;
import com.calygam.back.models.UserEntity;

@Component
public class RewardUtils {
	
	//caio <- temq ue retornar a userEntity aqui pra parte de entregar a atividade
	
	public UserEntity applyModifierInReward(UserEntity user,RewardPackageEntity reward, PetEntity pet, PetOutfitEntity petOutfit) {
		Long rewardModifiedMoney =Math.round(reward.getRewardPackageMoney()*pet.getPetBoostMoney()) + petOutfit.getPetOutfitPlusMoney();
		user.setUserMoney(user.getUserMoney()+rewardModifiedMoney);
		
		
		Long rewardModifiedXp =Math.round(reward.getRewardPackageXp()*pet.getPetBoostXp()) + petOutfit.getPetOutfitPlusXp();
		user.setXp(user.getXp()+rewardModifiedXp);
		
		Long rewardModifiedFood =Math.round(reward.getRewardPackageFood()*pet.getPetBoostFood()) + petOutfit.getPetOutfitPlusFood();
		user.setUserFood(user.getUserFood()+rewardModifiedFood);
		
		return user;
		
	}
	
	
}
