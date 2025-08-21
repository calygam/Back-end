package com.calygam.back.mappers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.dtos.ActivityProgressResponseDTO;
import com.calygam.back.dtos.ProgressBarTrailDTO;
import com.calygam.back.dtos.ProgressSubmitActivityDTO;
import com.calygam.back.projections.ProgressAssignProjection;
import com.calygam.back.projections.ProgressBarTrailProjection;
import com.calygam.back.projections.SubmitedProgressProjection;


@Component
public class ActivityProgressMapper {
	public ActivityProgressResponseDTO convertToDTO(ProgressAssignProjection projection) {
        ActivityProgressResponseDTO dto = new ActivityProgressResponseDTO();
        dto.setProgressId(projection.getProgressId());
        dto.setUserId(projection.getUserId());
        dto.setTrailId(projection.getTrailId());
        dto.setActivityId(projection.getActivityId());
        dto.setTrailStatus(projection.getTrailStatus());
        dto.setActivityStatus(projection.getActivityStatus());
        dto.setCreatedAt(projection.getCreatedAt());
        dto.setUpdatedAt(projection.getUpdatedAt());
        dto.setUnlockedActivities(projection.getUnlockedActivities());
      
     
        return dto;
	}
        
        public ProgressBarTrailDTO convertToProgressBarDTO(ProgressBarTrailProjection projection) {
        	ProgressBarTrailDTO dto = new ProgressBarTrailDTO();
            dto.setTotalActivitiesCompleted(projection.getTotalActivitiesCompleted());
            dto.setTotalActivities(projection.getTotalActivities());
            
          
         
            return dto;
  
  
}
}
