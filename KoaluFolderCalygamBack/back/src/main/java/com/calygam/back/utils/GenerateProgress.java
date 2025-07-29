package com.calygam.back.utils;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calygam.back.dtos.ActivityProgressDTO;
import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.models.ActivityProgressEntity;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.repositories.ProgressRepository;
import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.repositories.UsersRepository;

@Component
public class GenerateProgress {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TrailRepository trailRepository;
	
	@Autowired 
	private ProgressRepository progressRepository;
	

	
	public ActivityProgressEntity assignProgress(ActivityProgressDTO dto,  boolean isFirstActivity,Long userId,TrailEntity trail) {
	    ActivityProgressEntity progress = new ActivityProgressEntity();
	    
	    

	    progress.setUser(dto.getUser());
	    progress.setTrail(dto.getTrail());
	    progress.setActivity(dto.getActivity());
	    progress.setTrailStatus(StatusOfLife.ENABLE);
	    progress.setUnlockedActivities(0L);
	    progress.setCreatedAt(LocalDate.now());
	    
	    if(trail.getUser().getUserId().equals(userId)){
	   
	    	progress.setActivityStatus(StatusOfLife.COMPLETE);
	    	return progress;
	    }else {

	    if (isFirstActivity) {
	    	trail.setTrailVacancy(trail.getTrailVacancy()+1);
	        progress.setActivityStatus(StatusOfLife.ENABLE);
	    } else {
	        progress.setActivityStatus(StatusOfLife.DESABLED);
	    }
	    }
	    return progress;
	}

}
