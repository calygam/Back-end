package com.calygam.back.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.ActivityDTO;
import com.calygam.back.mappers.ActivityMapper;
import com.calygam.back.projections.ActivityProjection;
import com.calygam.back.repositories.ActivityRepository;

@Service
public class ActivityService {
	
	@Autowired 
	private ActivityRepository activityRepository;
	
	@Autowired 
	private ActivityMapper activityMapper;
	
	public List<ActivityDTO> findActivitiesPerTrailIdService(Long trailId){
		List<ActivityProjection> activities = activityRepository.findActivitiesPerTargetTrailId(trailId);
		Long activitiesCount = activityRepository.countTotalActivitiesByTrailId(trailId);
		
		List<ActivityDTO> activityDTOs = 
				activities.stream()
				.map(atv -> activityMapper.toDTO(atv,activitiesCount))
				.collect(Collectors.toList()) ;
		
		return activityDTOs;
	}
	
}
