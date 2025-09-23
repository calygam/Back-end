package com.calygam.back.mappers;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.dtos.ProgressSubmitActivityDTO;
import com.calygam.back.dtos.SubmissionsDTO;
import com.calygam.back.dtos.SubmittedActivityUserDTO;
import com.calygam.back.projections.SubmissionArchivesForTeacherProjection;
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
	
	public SubmissionsDTO transferToSubmissionDTO(SubmissionArchivesForTeacherProjection p){
		SubmissionsDTO submissionsDTO = new SubmissionsDTO();
		
		submissionsDTO.setSubmissionId(p.getSubmissionId());
		submissionsDTO.setSubmissionArchiveUrl(buildUrlUtil("/file/read/submission/",p.getSubmissionArchiveName()));
		submissionsDTO.setSubmissionOriginalName(p.getSubmissionOriginalName());
		return submissionsDTO;
	}
	
public SubmittedActivityUserDTO deliveredToDTO(SubmissionArchivesForTeacherProjection p, List<SubmissionsDTO> submissions){
		
		SubmittedActivityUserDTO progressSubmitActivityDTO = new SubmittedActivityUserDTO();
		progressSubmitActivityDTO.setUserName(p.getUserName());
		progressSubmitActivityDTO.setUserId(p.getUserId());
		progressSubmitActivityDTO.setUserArchiveUrl(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/file/read/user/")        
                .path(p.getUserArchiveName())
                .toUriString());
	
		progressSubmitActivityDTO.setSubmissions(submissions);
		return progressSubmitActivityDTO;
		
	}

public String buildUrlUtil(String path,String archiveName) {
	return (archiveName==null || archiveName.isEmpty())?null:ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/file/read/submission/")        
            .path(archiveName)
            .toUriString();
}
	
	
	

}
