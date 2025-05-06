package com.calygam.back.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.ActivityDTO;
import com.calygam.back.dtos.TrailDTO;
import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.exceptions.UserAlreadExistsException;
import com.calygam.back.models.ActivityEntity;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TrailService {
	
	@Autowired
	TrailRepository trailRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	private MakeUploadAndDownloadArchive makeUploadAndDownloadArchive;
	
	public TrailDTO createNewTrail(Long userId,TrailDTO trailDTO) throws IOException {
		
		if(trailRepository.findExistentTrail(userId,trailDTO.getTrailName())!=null) {
			throw new UserAlreadExistsException("Está trilha já foi registrada por você :/");
		}
		TrailEntity trailEntity = new TrailEntity();
		
		trailEntity.setTrailName(trailDTO.getTrailName());
		trailEntity.setTrailDescription(trailDTO.getTrailDescription());
		trailEntity.setTrailPrice(trailDTO.getTrailPrice());
		trailEntity.setTrailCreatedDate(LocalDate.now());
		trailEntity.setTrailUpdatedDate(null);
		
	
		trailEntity.setTrailPassword(	
				trailDTO.getTrailPassword()!=null?
						new BCryptPasswordEncoder().encode(trailDTO.getTrailPassword()):null);
		trailEntity.setTrailVacancy(Long.valueOf(0));
		trailEntity.setTrailVacancies(trailDTO.getTrailVacancies());
		
		UserEntity userEntity = usersRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + userId));;
		
		trailEntity.setUser(userEntity);
		
	
        for (ActivityDTO dto : trailDTO.getActivities()) {
            ActivityEntity activityEntity = new ActivityEntity();
            activityEntity.setActivityName(dto.getActivityName());
            activityEntity.setActivityDescription(dto.getActivityDescription());
            activityEntity.setActivityPrice(dto.getActivityPrice());
            activityEntity.setActivityDifficulty(dto.getActivityDifficulty());
            activityEntity.setActivityStatus(StatusOfLife.DESABLED);
            activityEntity.setActivityCreatedAt(LocalDate.now());
            activityEntity.setActivityUpdatedAt(null);
            activityEntity.setTrail(trailEntity); 
            trailEntity.getActivities().add(activityEntity);
        }
			
		
		
    	makeUploadAndDownloadArchive.saveArchive(trailDTO.getTrailFileImage(),trailEntity,trailRepository);
		
		trailEntity = trailRepository.save(trailEntity);
	
		return new TrailDTO(trailEntity);
	}
	
	public List<TrailDTO> ReadAllTrails() {
	    return trailRepository.findAllTrailsWithActivities();
	}
}
