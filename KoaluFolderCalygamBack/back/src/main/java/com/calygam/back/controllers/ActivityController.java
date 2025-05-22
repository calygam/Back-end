package com.calygam.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.dtos.ActivityDTO;
import com.calygam.back.services.ActivityService;


@RestController
@RequestMapping(value ="activities")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@GetMapping("/trail/{trailId}")
	public List<ActivityDTO> getActivitiesBy(@PathVariable Long trailId){
		return activityService.findActivitiesPerTrailIdService(trailId);
	}

}
