package com.calygam.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.RewardDTO;
import com.calygam.back.models.RewardPackageEntity;
import com.calygam.back.projections.RewardPackageProjection;
import com.calygam.back.services.RewardPackageService;
import com.calygam.back.sucesshandlers.ApiSucessHandler;

@RestController
@RequestMapping("/reward")
public class RewardPackageController {
	
	@Autowired
	private RewardPackageService rewardPackageService;
	
	@PostMapping("/create")
	public ApiSucessHandler<RewardPackageEntity> createANewRewardController(@RequestBody RewardDTO dto){
		return rewardPackageService.CreateANewReward(dto);
	}
	@GetMapping("/read-all")
	public List<RewardPackageProjection> readAllRewardsController(){
		return rewardPackageService.readAllRewards();
	}
}
