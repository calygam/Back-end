package com.calygam.back.mappers;

import org.springframework.stereotype.Component;

import com.calygam.back.dtos.ActivityProgressResponseDTO;
import com.calygam.back.projections.ProgressAssignProjection;

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
}
