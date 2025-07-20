package com.calygam.back.mappers;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.dtos.ProgressSubmitActivityDTO;
import com.calygam.back.projections.SubmissionArchivesProjection;

@Component
public class SubmissionMappers {
	
	
	public ProgressSubmitActivityDTO submittedToDTO(SubmissionArchivesProjection p){
		
		ProgressSubmitActivityDTO progressSubmitActivityDTO = new ProgressSubmitActivityDTO();
		progressSubmitActivityDTO.setSubmissionId(p.getSubmissionId());
		progressSubmitActivityDTO.setActivitySubmitedFile(ServletUriComponentsBuilder
	                .fromCurrentContextPath()
	                .path("/file/read/submission/")        
	                .path(p.getArchiveName())
	                .toUriString());
		progressSubmitActivityDTO.setActivityOriginalFileName(p.getOriginalName());
		
		return progressSubmitActivityDTO;
		
	}
	

}
