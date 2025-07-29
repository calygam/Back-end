package com.calygam.back.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.ActivityProgressDTO;
import com.calygam.back.dtos.ActivityProgressResponseDTO;
import com.calygam.back.dtos.ProgressSubmitActivityDTO;
import com.calygam.back.enums.ItemCatalogInventoryEnum;
import com.calygam.back.enums.StatusOfLife;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.exceptions.ExcededMaxDelimiter;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.exceptions.UserAlreadExistsException;
import com.calygam.back.exceptions.UserNotIdentifiedException;
import com.calygam.back.mappers.ActivityProgressMapper;
import com.calygam.back.models.ActivityEntity;
import com.calygam.back.models.ActivityProgressEntity;
import com.calygam.back.models.ApprenticeInventoryEntity;
import com.calygam.back.models.DailyFlagsEntity;
import com.calygam.back.models.PetEntity;
import com.calygam.back.models.PetOutfitEntity;
import com.calygam.back.models.RewardPackageEntity;
import com.calygam.back.models.SubmissionEntity;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.ActivityProgressProjection;
import com.calygam.back.projections.ProgressAssignProjection;
import com.calygam.back.repositories.ActivityRepository;
import com.calygam.back.repositories.ApprenticeInventoryRepository;
import com.calygam.back.repositories.DailyFlagsRepository;
import com.calygam.back.repositories.PetOutfitRepository;
import com.calygam.back.repositories.PetRepository;
import com.calygam.back.repositories.ProgressRepository;
import com.calygam.back.repositories.RewardRepository;
import com.calygam.back.repositories.SubmissionsRepository;
import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.utils.GenerateProgress;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;
import com.calygam.back.utils.RewardUtils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProgressActivityService {
	
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
	private RewardRepository rewardRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private PetOutfitRepository petOutfitRepository;
	
	@Autowired
	private ApprenticeInventoryRepository apprenticeInventoryRepository;
	
	@Autowired
	private SubmissionsRepository submissionsRepository;
	
	@Autowired
	private ActivityProgressMapper activityProgressMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DailyFlagsService dailyFlagsService;
	
	@Autowired
	private DailyFlagsRepository dailyFlagsRepository;
	
	
	
	@Autowired
	private MakeUploadAndDownloadArchive makeUploadAndDownloadArchive;
	
	@Autowired
	private RewardUtils rewardUtils;
	
	@Transactional
	public List<ActivityProgressResponseDTO> progressAssignStudent(String trailPassword,Long userId, Long trailId)  {
	    UserEntity userEntity = usersRepository.findEntityByUserId(userId)
	        .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
	 

	    TrailEntity trailEntity = trailRepository.findById(trailId)
	        .orElseThrow(() -> new EntityNotFoundException("Trilha não encontrada"));
	    
	    
	    if(userEntity.getUserRole().ordinal()<3 && !trailEntity.getUser().getUserId().equals(userId)) {
	    	throw new UserAlreadExistsException("você é um professor mas não pertence a essa trilha!");
	    }
	    
	    
	    AtomicInteger index = new AtomicInteger(0);
	    if(trailEntity.getTrailVacancy()>=trailEntity.getTrailVacancies()) {
	    	throw new ExcededMaxDelimiter("Vagas excedidas!");
	    }
	    
	    if(passwordEncoder.matches(trailPassword, trailEntity.getTrailPassword())) {
	    	 Boolean hasProgress = progressRepository
	    	            .existsByUserIdAndTrailId(userId, trailId);
	    	if(!hasProgress) {
	    		
	    		  List<ActivityProgressEntity> progressList = trailEntity.getActivities().stream()
	    			        .map(activity -> {
	    			            boolean isFirst = index.getAndIncrement() == 0;

	    			            ActivityProgressDTO dto = new ActivityProgressDTO();
	    			            dto.setUser(userEntity);
	    			            dto.setTrail(trailEntity);
	    			            dto.setActivity(activity);

	    			            return generateProgress.assignProgress(dto, isFirst,userId,trailEntity);
	    			        })
	    			        .collect(Collectors.toList());

	    			     progressRepository.saveAll(progressList);
	    	
	    	    }
	    	
	    List<ProgressAssignProjection> progressProjections = progressRepository
	            .findProgressByUserIdAndTrailId(userId, trailId);

	
	        return progressProjections.stream()
	            .map(p-> activityProgressMapper.convertToDTO(p))
	            .collect(Collectors.toList());
	    }else {
	    	throw new BadCredentialsException("Senha incorreta para acesso à trilha.");
	    }
	}
	
	public Map<String, Object> findProgressOfOneUser(Long userId, Long trailId) {
        List<ProgressAssignProjection> progressProjections = progressRepository
                .findProgressByUserIdAndTrailId(userId, trailId);

        Long activitiesCompleted = progressRepository.countTotalActivitiesCompleted(userId, trailId);

        if (!progressProjections.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("progressList", progressProjections.stream()
                    .map(p->activityProgressMapper.convertToDTO(p))
                    .collect(Collectors.toList()));
            response.put("activitiesCompleted", activitiesCompleted != null ? activitiesCompleted : 0L);
            return response;
        }
        throw new UserNotIdentifiedException("O usuário não faz parte dessa trilha :/");
    }
	
	 public Optional<ActivityProgressProjection> getCurrentActivityEnable(Long userId, Long trailId) {
		 if(progressRepository.findMostRecentActivityWithProgress(userId, trailId).isEmpty()) {
			 return progressRepository.findMostRecentActivityCompletedWithProgress(userId, trailId);
		 }else {
			 return progressRepository.findMostRecentActivityWithProgress(userId, trailId);
		 }
	      
	    }
	 
	 
	 
	 
	 
	 //Caio<- vamos enviar a atividade do aluno
	 @Transactional
	 public ResponseEntity<String> submitActivityForTeacher(Long userId, Long trailId, Long activityId, ProgressSubmitActivityDTO dto) throws IOException {
	     UserEntity userEntity = usersRepository.findEntityByUserId(userId)
	             .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

	     if (userEntity.getUserRole() == UserRoleEnum.INSTRUTOR) {
	         throw new UserAlreadExistsException("você é um professor!");
	     }
	     
	     DailyFlagsEntity tokenEntity = dailyFlagsService.collectOrGenerateFlags(userEntity);
	     if (tokenEntity.getUserFlags() <= 0) {
	         throw new ExcededMaxDelimiter("Limite diário de bandeiras gastas. Tente novamente amanhã.");
	     }


	     ActivityEntity activityEntity = activityRepository.findActivityByTrailIdAndActivityId(activityId, trailId)
	             .orElseThrow(() -> new EntityNotFoundException("Atividade não identificada!"));

	     Long progressMinePerStatus= progressRepository.findMinActivityIdPerMAXStatus(userId, trailId,(long) 0) ;
	     
 	     ActivityProgressEntity progressEntityCompleted = progressRepository.findByUserTrailAndActivity(userId, trailId, activityId)
	             .orElseThrow(() -> new EntityNotFoundException("Progresso não identificado linha"));
 	     
	      if(progressEntityCompleted.getActivityStatus() == StatusOfLife.COMPLETE && activityId>0) {
	          // Verificar limite de 5 submissões


		         if (dto.getActivityFiles() == null || dto.getActivityFiles().isEmpty()) {
		             throw new IllegalArgumentException("Nenhum arquivo enviado!");
		         }
		         dto.getActivityFiles().forEach(targetArchive -> {
			         Long submissionCount = submissionsRepository.countSubmissionsByProgressId(progressEntityCompleted.getProgressId());
			         if (submissionCount >= 5) {
			             throw new ExcededMaxDelimiter("Limite de 5 entregas atingido para esta atividade!");
			         }
		             SubmissionEntity submissionEntity = new SubmissionEntity();
		             submissionEntity.setProgress(progressEntityCompleted);
		             try {
		                 makeUploadAndDownloadArchive.saveArchive(targetArchive, submissionEntity, submissionsRepository);
		             } catch (IOException e) {
		                 throw new RuntimeException("Erro ao salvar arquivo: " + targetArchive.getOriginalFilename());
		             }
		         });
		         

		         
		         return null;
	      }
	     ActivityProgressEntity progressEntity = progressRepository.findByUserTrailAndActivity(userId, trailId, progressMinePerStatus)
	             .orElseThrow(() -> new EntityNotFoundException("Progresso não identificado linha"));
	     
	     System.out.println("""
	             ////////////////////////////////////////
	             //////////////////////////////////////// ActivityId + = """ + progressEntity.getActivity().getActivityId());

	     if (progressEntity.getActivityStatus() == StatusOfLife.ENABLE && progressEntity.getUser().getUserRole() == UserRoleEnum.ALUNO) {
	         // Verificar limite de 5 submissões
	         Long submissionCount = submissionsRepository.countSubmissionsByProgressId(progressEntity.getProgressId());
	         if (submissionCount >= 5) {
	             throw new IllegalStateException("Limite de 5 entregas atingido para esta atividade!");
	         }

	         progressEntity.setActivityStatus(StatusOfLife.COMPLETE);
	         progressEntity.setUpdatedAt(LocalDate.now());
	         progressRepository.save(progressEntity);
	         RewardPackageEntity rewardPackageEntity = rewardRepository.findById(activityEntity.getRewardPackage().getRewardPackageId())
	            		.orElseThrow(()-> new SoftNotFoundException("Eita!, recompensa não encontrada :("));
	         ApprenticeInventoryEntity inventory = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryEquippedTrue(userId,ItemCatalogInventoryEnum.PET).orElse(null) ;
	         if(inventory!=null) {
	        	  PetEntity obtainPet = petRepository.findById(inventory.getApprenticeInventoryItemId()).orElseThrow(()-> new SoftNotFoundException("Pet não encontrado!"));
	        	  List<PetOutfitEntity> obtainOutfitsPet = petOutfitRepository.findByPet_petId(obtainPet.getPetId());
	        	  ApprenticeInventoryEntity obtainEqquipedSkin =null;
	        	  for( Integer i=0;i<obtainOutfitsPet.size();i++) {
	        		  PetOutfitEntity getterOfOutfit = obtainOutfitsPet.get(i);
	        		  obtainEqquipedSkin = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, ItemCatalogInventoryEnum.SKIN,getterOfOutfit.getPetOutfitId()).orElseThrow(()-> new SoftNotFoundException("Pet recusou o acesso ao inventário -recomepsa"));
	        		  if(obtainEqquipedSkin.isApprenticeInventoryEquipped()) {
	        			  break;
	        		  }
	        	  }
	        	  PetOutfitEntity petOutfitEntity = petOutfitRepository.findById(obtainEqquipedSkin.getApprenticeInventoryItemId()).orElseThrow(()-> new SoftNotFoundException("Não encontramos o traje"));
	        	  UserEntity userModifiedReward = rewardUtils.applyModifierInReward(userEntity, rewardPackageEntity, obtainPet, petOutfitEntity);
	        	  usersRepository.save(userModifiedReward);
	        	  System.out.println("MODIFICADA - - - - - Recompensa modificada "+" o xp = "+ userModifiedReward.getXp()+" e o money = " +userModifiedReward.getUserMoney()+" e a food = "+ userModifiedReward.getUserFood() );
	        	  
	        	  
	         }else {
	        	    userEntity.setUserFood(userEntity.getUserFood()+rewardPackageEntity.getRewardPackageFood());
	   	         userEntity.setUserMoney(userEntity.getUserMoney() +rewardPackageEntity.getRewardPackageMoney());
	   	         userEntity.setXp(userEntity.getXp() + rewardPackageEntity.getRewardPackageXp());
	   	         usersRepository.save(userEntity);
	   	      System.out.println("NOOOOOORMAL" );
	         }
	            
	     
	       
	        
	         if (dto.getActivityFiles() == null || dto.getActivityFiles().isEmpty()) {
	             throw new IllegalArgumentException("Nenhum arquivo enviado!");
	         }
	     
	         if (progressEntity.getActivityStatus() == StatusOfLife.COMPLETE) {
	             dto.getActivityFiles().forEach(targetArchive -> {
	                 SubmissionEntity submissionEntity = new SubmissionEntity();
	                 submissionEntity.setProgress(progressEntity);
	                 try {
	                     makeUploadAndDownloadArchive.saveArchive(targetArchive, submissionEntity, submissionsRepository);
	                 } catch (IOException e) {
	                     throw new RuntimeException("Erro ao salvar arquivo: " + targetArchive.getOriginalFilename());
	                 }
	             });
	             Long progressMinePerStatusDisable= progressRepository.findMinActivityIdPerStatus(userId, trailId,(long) 1) ;
	             if(progressMinePerStatusDisable == null) {
	            	 return null;
	             }
	             ActivityProgressEntity progressEntityAdvanced = progressRepository.findByUserTrailAndActivity(userId, trailId,progressMinePerStatusDisable)
	                     .orElseThrow(() -> new EntityNotFoundException("Progresso não identificado linha - 204"));
	             

	             progressEntityAdvanced.setActivityStatus(StatusOfLife.ENABLE);
	             progressEntityAdvanced.setUpdatedAt(LocalDate.now());
	             progressRepository.save(progressEntityAdvanced);
	         }
	     
	     } else if (progressEntity.getActivityStatus() == StatusOfLife.COMPLETE) {
	    	 return null;
	     
	     } else {
	         throw new EntityNotFoundException("Usuario não é um aluno " + " atividade status " + progressEntity.getActivity().getActivityStatus().ordinal() + " Ordinal do usuário = " + progressEntity.getUser().getUserRole().ordinal());
	     }
	     tokenEntity.setUserFlags(tokenEntity.getUserFlags() - 1);
	     dailyFlagsRepository.save(tokenEntity);
	     return ResponseEntity.ok("Atividade Enviada com sucesso!");
	 }
	
}


