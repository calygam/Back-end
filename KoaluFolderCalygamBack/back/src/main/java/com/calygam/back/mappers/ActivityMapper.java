package com.calygam.back.mappers;

import org.springframework.stereotype.Component;

import com.calygam.back.dtos.ActivityDTO;
import com.calygam.back.projections.ActivityProjection;

@Component
public class ActivityMapper {
	
	public  ActivityDTO toDTO(ActivityProjection p,Long count,Long activitiesSize) {
		ActivityDTO activityDTO = new ActivityDTO();
		//caio <- vamos de maneira organizada mapear e isolar toda a parte que irá retornar na dto
		activityDTO.setActivityId(p.getActivityId());
		activityDTO.setActivityName(p.getActivityName());
		activityDTO.setActivityDescription(p.getActivityDescription());
		activityDTO.setActivityPrice(p.getActivityPrice());
		activityDTO.setActivityDifficulty(p.getActivityDifficulty());
		activityDTO.setActivityStatus(p.getActivityStatus());
		activityDTO.setActivityCreatedAt(p.getActivityCreatedAt());
		activityDTO.setActivityUpdatedAt(p.getActivityUpdatedAt());
		if(count != null && count !=0 && count>0) {
		activityDTO.setActivityCont(count);
		}
		if(activitiesSize !=null && activitiesSize>=0) {
			activityDTO.setActivitiesCompleted(activitiesSize);
		}
		
		return activityDTO;
		
	}
	
	public  ActivityDTO toDTOClean(ActivityProjection p) {
		ActivityDTO activityDTO = new ActivityDTO();
		//caio <- vamos de maneira organizada mapear e isolar toda a parte que irá retornar na dto
		activityDTO.setActivityId(p.getActivityId());
		activityDTO.setActivityName(p.getActivityName());
		activityDTO.setActivityDescription(p.getActivityDescription());
		activityDTO.setActivityPrice(p.getActivityPrice());
		activityDTO.setActivityDifficulty(p.getActivityDifficulty());
		activityDTO.setActivityStatus(p.getActivityStatus());
		activityDTO.setActivityCreatedAt(p.getActivityCreatedAt());
		activityDTO.setActivityUpdatedAt(p.getActivityUpdatedAt());
	
		
		return activityDTO;
		
	}
	
}
