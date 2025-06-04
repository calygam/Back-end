package com.calygam.back.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.ActivityDTO;
import com.calygam.back.dtos.TrailDTO;
import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.exceptions.UnauthorizedAcessUserException;
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
		
		if(trailRepository.findExistentTrail(trailDTO.getTrailName())!=null) {
			throw new UserAlreadExistsException("Está trilha já foi registrada");
		}
		TrailEntity trailEntity = new TrailEntity();
		
		trailEntity.setTrailName(trailDTO.getTrailName());
		trailEntity.setTrailDescription(trailDTO.getTrailDescription());
		trailEntity.setTrailStatus(StatusOfLife.BUILDING);
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
		userEntity.setXp(userEntity.getXp()+50);
		
	
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
            userEntity.setXp(userEntity.getXp()+50);
        }
			
		
		
    	makeUploadAndDownloadArchive.saveArchive(trailDTO.getTrailFileImage(),trailEntity,trailRepository);
		usersRepository.save(userEntity);
		trailEntity = trailRepository.save(trailEntity);
	
		return new TrailDTO(trailEntity);
	}
	
	public List<TrailDTO> ReadAllTrailsOfTeachers() {
	    return trailRepository.findAllTrailsWithActivitiesOfTeachers();
	}
	public List<TrailDTO> ReadAllTrailsOfOneTeacher(Long userId) {
		   List<TrailDTO> trails = trailRepository.findAllTrailsWithActivitiesPerTeacher(userId);
		   return trails;
	}
	
	public TrailDTO ReadTrailById(Long trailId) {
		   TrailEntity entity = trailRepository.findById(trailId)
			        .orElseThrow(() -> new RuntimeException("Trail not found"));

			    return new TrailDTO(entity);
	}
	
	public TrailDTO updateInfoTrailByExistsId(Long userId,Long trailId,TrailDTO trailDTO) throws IOException  {
		
		//caio<-primeiro  vamos buscar uma trilha pelo id
		TrailEntity trailEntity = trailRepository.findById(trailId)
				.orElseThrow(()-> new EntityNotFoundException("Trilha a editar não encontrada :("));
		
		if(!trailEntity.getUser().getUserId().equals(userId)) {
			throw new UnauthorizedAcessUserException("Usuário não autorizado para editar esta trilha");
		}
		UserEntity userEntity = usersRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + userId));
		
		// Validar o nome da trilha
	    if (trailDTO.getTrailName() != null && !trailDTO.getTrailName().isEmpty()) {
	        // Verificar se o nome é diferente do atual e já existe em outra trilha
	        if (!trailDTO.getTrailName().equals(trailEntity.getTrailName())) {
	            TrailDTO existingTrail = trailRepository.findExistentTrail(trailDTO.getTrailName());
	            if (existingTrail != null && !existingTrail.getTrailId().equals(trailId)) {
	                throw new UserAlreadExistsException("Está trilha já foi registrada");
	            }
	        }
	        trailEntity.setTrailName(trailDTO.getTrailName());
	    }
		if(trailDTO.getTrailDescription() !=null) {
			trailEntity.setTrailDescription(trailDTO.getTrailDescription());
		}
		if(trailDTO.getTrailPrice() !=null) {
			trailEntity.setTrailPrice(trailDTO.getTrailPrice());
		}
		if (trailDTO.getTrailPassword() != null && !trailDTO.getTrailPassword().isEmpty()) {
		    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    
		    if (!encoder.matches(trailDTO.getTrailPassword(), trailEntity.getTrailPassword())) {
		        String encryptedPassword = encoder.encode(trailDTO.getTrailPassword());
		        trailEntity.setTrailPassword(encryptedPassword);
		    }
		}
		trailEntity.setTrailUpdatedDate(LocalDate.now());
		
		if(trailDTO.getTrailFileImage()!=null) {
			
			makeUploadAndDownloadArchive.deleteFile(trailEntity.getArchiveName());
			
			makeUploadAndDownloadArchive.saveArchive(trailDTO.getTrailFileImage(),trailEntity,trailRepository)
			;
		}
		
		String code = trailDTO.getCalygamCode();
		if (code != null && code.trim().equals("calygam up trail")) {
		    trailEntity.setTrailStatus(StatusOfLife.ENABLE);
		}
		
		
		
		//caio<- agora vem a lógica para atualizarmos as atividades ou criar novas atividades
		if (trailDTO.getActivities() != null && !trailDTO.getActivities().isEmpty()) {
		    // Mapear as atividades existentes da trilha
		    Map<Integer, ActivityEntity> activitiesPresentInBase = trailEntity.getActivities().stream()
		            .collect(Collectors.toMap(ActivityEntity::getActivityId, Function.identity()));

		    List<ActivityEntity> updatedActivities = new ArrayList<>();

		    for (ActivityDTO activityDTO : trailDTO.getActivities()) {
		        ActivityEntity activityEntity;
		        
		        if (activityDTO.getActivityId() != null && activitiesPresentInBase.containsKey(activityDTO.getActivityId())) {
		        
		            activityEntity = activitiesPresentInBase.get(activityDTO.getActivityId());

		            if (activityDTO.getActivityName() != null) {
		                activityEntity.setActivityName(activityDTO.getActivityName());
		            }
		            if (activityDTO.getActivityDescription() != null) {
		                activityEntity.setActivityDescription(activityDTO.getActivityDescription());
		            }
		            if (activityDTO.getActivityPrice() != null) {
		                activityEntity.setActivityPrice(activityDTO.getActivityPrice());
		            }
		            if (activityDTO.getActivityDifficulty() != null) {
		                activityEntity.setActivityDifficulty(activityDTO.getActivityDifficulty());
		            }
		            if (activityDTO.getActivityStatus() != null) {
		                activityEntity.setActivityStatus(activityDTO.getActivityStatus());
		            }

		            activityEntity.setActivityUpdatedAt(LocalDate.now());
		            updatedActivities.add(activityEntity);

		            activitiesPresentInBase.remove(activityDTO.getActivityId());
		        } else {
		        	userEntity.setXp(userEntity.getXp()+25);
		            activityEntity = new ActivityEntity();
		            activityEntity.setActivityName(activityDTO.getActivityName());
		            activityEntity.setActivityDescription(activityDTO.getActivityDescription());
		            activityEntity.setActivityPrice(activityDTO.getActivityPrice());
		            activityEntity.setActivityDifficulty(activityDTO.getActivityDifficulty());
		            activityEntity.setActivityStatus(StatusOfLife.DESABLED);
		            activityEntity.setActivityCreatedAt(LocalDate.now());
		            activityEntity.setTrail(trailEntity);

		            updatedActivities.add(activityEntity);
		        }
		    }

		    for (ActivityEntity toBeRemoved : activitiesPresentInBase.values()) {
		        toBeRemoved.setTrail(null); 
		    }

		    trailEntity.getActivities().clear();
		    trailEntity.getActivities().addAll(updatedActivities);
		}
		trailEntity = trailRepository.save(trailEntity);
		usersRepository.save(userEntity);
		return new TrailDTO(trailEntity);
	}
	
	
	
	
	
	   
	
	
	
	public void deleteActivity(Long userId, Long trailId, Integer activityId) {
	        TrailEntity trail = trailRepository.findById(trailId)
	            .orElseThrow(() -> new EntityNotFoundException("Trilha não encontrada"));
	        if (!trail.getUser().getUserId().equals(userId)) {
	            throw new UnauthorizedAcessUserException("Não autorizado");
	        }

	        boolean removed = trail.getActivities().removeIf(a -> 
	            a.getActivityId().equals(activityId)
	        );
	        if (!removed) {
	            throw new EntityNotFoundException("Atividade não encontrada");
	        }

	      
	        trailRepository.save(trail);
	    }

	    public void deleteTrail(Long userId, Long trailId) {
	        TrailEntity trail = trailRepository.findById(trailId)
	            .orElseThrow(() -> new EntityNotFoundException("Trilha não encontrada"));
	        if (!trail.getUser().getUserId().equals(userId)) {
	            throw new UnauthorizedAcessUserException("Não autorizado");
	        }
	        trailRepository.delete(trail);
	    }
	}

