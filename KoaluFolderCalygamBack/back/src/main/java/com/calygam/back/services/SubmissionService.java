package com.calygam.back.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.ProgressSubmitActivityDTO;
import com.calygam.back.exceptions.ExcededMaxDelimiter;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.mappers.SubmissionMappers;
import com.calygam.back.models.SubmissionEntity;
import com.calygam.back.repositories.ActivityRepository;
import com.calygam.back.repositories.ProgressRepository;
import com.calygam.back.repositories.SubmissionsRepository;
import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.utils.GenerateProgress;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;

import jakarta.transaction.Transactional;

@Service
public class SubmissionService {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TrailRepository trailRepository;
	
	@Autowired
	private GenerateProgress generateProgress;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private ProgressRepository progressRepository;
	
	@Autowired
	private SubmissionsRepository submissionsRepository;
	
	@Autowired
	private MakeUploadAndDownloadArchive makeUploadAndDownloadArchive;
	
	@Autowired 
	private SubmissionMappers mapper;
	
	public List<ProgressSubmitActivityDTO> ListenerOfDowloadableArchivesSubmitedService(Long progressId){
		return submissionsRepository.findArchivesForDownloadAcessPerProgressId(progressId)
				.stream()
				.map(tbs-> mapper.submittedToDTO(tbs))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public Boolean deleteSubmission(Long submissionId,Long progressId) {
	     Long submissionCount = submissionsRepository.countSubmissionsByProgressId(progressId);
	     if(submissionId<2) {
	    	 throw new ExcededMaxDelimiter("*Ops!, Deve existir pelo menos um arquivo entregue!");
	     }
		SubmissionEntity submissionEntity = submissionsRepository.findById(submissionId)
				.orElseThrow(()-> new SoftNotFoundException("Entrega não identificada!") );
   
		
		try {
			makeUploadAndDownloadArchive.deleteFile(submissionEntity.getArchiveName());
			submissionsRepository.delete(submissionEntity);
		}catch(IOException e) {
			throw new SoftNotFoundException(e.getMessage());
			
		}
		return true;
	}
}
