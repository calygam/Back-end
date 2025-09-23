package com.calygam.back.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.ProgressSubmitActivityDTO;
import com.calygam.back.dtos.SubmissionsDTO;
import com.calygam.back.dtos.SubmittedActivityUserDTO;
import com.calygam.back.exceptions.ExcededMaxDelimiter;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.exceptions.UnauthorizedAcessUserException;
import com.calygam.back.mappers.SubmissionMappers;
import com.calygam.back.models.ActivityEntity;
import com.calygam.back.models.ActivityProgressEntity;
import com.calygam.back.models.SubmissionEntity;
import com.calygam.back.projections.SubmissionArchivesForTeacherProjection;
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
	
	public List<SubmittedActivityUserDTO> ListenerSubmittedArchivesUser(Long userId, Long progressId) {
		ActivityProgressEntity ac = progressRepository.findById(progressId).orElseThrow(()-> new SoftNotFoundException("Usuário não encontrado"));
		ActivityEntity atv = activityRepository.findById(ac.getActivity().getActivityId()).orElseThrow(()-> new SoftNotFoundException("Usuário não encontrado"));
		
		
        return submissionsRepository.findArchivesForDownloadAcessPerProgressIdAndPhoto(atv.getActivityId()!=null?atv.getActivityId():0L)
                .stream()
                .collect(Collectors.groupingBy(p->p.getUserId()))
                .entrySet()
                .stream()
                .map(entry -> {
                    List<SubmissionArchivesForTeacherProjection> groupList = entry.getValue();
                    List<SubmissionsDTO> submissions = groupList.stream()
                            .map(mapper::transferToSubmissionDTO)
                            .collect(Collectors.toList());
                    return mapper.deliveredToDTO(groupList.get(0), submissions);
                })
                .collect(Collectors.toList());
    }
	@Transactional
	public Boolean deleteSubmission(Long submissionId,Long progressId) {
	     Long submissionCount = submissionsRepository.countSubmissionsByProgressId(progressId);
	     if(submissionCount<2) {
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
