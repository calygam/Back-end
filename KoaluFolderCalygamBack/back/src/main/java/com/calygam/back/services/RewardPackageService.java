package com.calygam.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.RewardDTO;
import com.calygam.back.exceptions.OneValueIsNullException;
import com.calygam.back.models.RewardPackageEntity;
import com.calygam.back.projections.RewardPackageProjection;
import com.calygam.back.repositories.RewardRepository;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

@Service
public class RewardPackageService {
	
	@Autowired
	private RewardRepository rewardRepository;
	
	public ApiSucessHandler<RewardPackageEntity> CreateANewReward(RewardDTO dto){
		RewardPackageEntity rewardPackageEntity = new RewardPackageEntity();
		if(dto.getRewardPackageMoney()==null || dto.getRewardPackageXp()==null || dto.getRewardPackageFood()==null || dto.getRewardPackageDifficulty()==null) {
			throw new OneValueIsNullException("Valores nulos não são aceitos tente novamente");
		}
		rewardPackageEntity.setRewardPackageMoney(dto.getRewardPackageMoney());
		rewardPackageEntity.setRewardPackageXp(dto.getRewardPackageXp());
		rewardPackageEntity.setRewardPackageFood(dto.getRewardPackageFood());
		rewardPackageEntity.setRewardActivityDifficulty(dto.getRewardPackageDifficulty());
		rewardRepository.save(rewardPackageEntity);
		
		return new ApiSucessHandler<RewardPackageEntity>(true,"recompensa criada com sucesso!", rewardPackageEntity);
		
	}
	public List<RewardPackageProjection> readAllRewards(){
		return rewardRepository.findAllRewards();
	}
}
