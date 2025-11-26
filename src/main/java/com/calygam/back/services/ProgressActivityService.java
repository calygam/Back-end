package com.calygam.back.services;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.calygam.back.enums.PetStatusEnergyEnum;
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
import com.calygam.back.models.ControlApprenticePetEntity;
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
import com.calygam.back.repositories.ControlApprenticePetRepository;
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
	
	@Autowired
	private ControlApprenticePetRepository controlApprenticePetRepository;
	
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
	    Boolean passwordValid = false;
	    if(trailPassword!=null && trailPassword!="" && !trailEntity.getTrailPassword().isEmpty()) {
	    		passwordValid = trailPassword.equals(trailEntity.getTrailPassword());
	    }
	    
	    if( passwordValid ||trailEntity.getTrailPassword()==null ||trailEntity.getTrailPassword()=="" || trailEntity.getTrailPassword().isEmpty()) {
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
			LocalDateTime targetHours = dailyFlagsRepository.getDatabaseCurrentTimeStamp();
	     if (tokenEntity.getUserFlags() <= 0) {
	    	 Duration duration = Duration.between(targetHours, tokenEntity.getDailyFlagCreatedAt().plus(Duration.ofMinutes(100)));
	    	 Long hour = duration.toHours();
	    	 long minuts = duration.toMinutes()% 60;
	         throw new ExcededMaxDelimiter("Entregas - Limite atingido. Tente novamente em " + hour+"h e " +minuts+" minutos.");
	     }


	     ActivityEntity activityEntity = activityRepository.findActivityByTrailIdAndActivityId(activityId, trailId)
	             .orElseThrow(() -> new EntityNotFoundException("Atividade não identificada!"));

	     Long progressMinePerStatus= progressRepository.findMinActivityIdPerMAXStatus(userId, trailId,(long) 0) ;
	     
 	     ActivityProgressEntity progressEntityCompleted = progressRepository.findByUserTrailAndActivity(userId, trailId, activityId)
	             .orElseThrow(() -> new EntityNotFoundException("Progresso não identificado linha"));
 	     
	      if(progressEntityCompleted.getActivityStatus() == StatusOfLife.COMPLETE && activityId>0) {
		         if (dto.getActivityFiles() != null && !dto.getActivityFiles().isEmpty()) {
		        	 dto.getActivityFiles().forEach(targetArchive -> {
				         Long submissionCount = submissionsRepository.countByProgress_ProgressIdAndArchiveNameIsNotNull(progressEntityCompleted.getProgressId());
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
		         }
		         if(dto.getActivityLinks() !=null && !dto.getActivityLinks().isEmpty()) {
		        	 dto.getActivityLinks().forEach(targetLink -> {
				         Long submissionCount = submissionsRepository.countByProgress_ProgressIdAndArchiveNameIsNull(progressEntityCompleted.getProgressId());
				         if (submissionCount >= 5) {
				             throw new ExcededMaxDelimiter("Limite de 10 entregas atingido para esta atividade!");
				         }
			             SubmissionEntity submissionEntity = new SubmissionEntity();
			             submissionEntity.setProgress(progressEntityCompleted);
			             try {
			            
			            	 submissionEntity.setSubmissionLink(targetLink);
			            	 submissionsRepository.save(submissionEntity);
			             } catch (Exception e) {
			                 throw new RuntimeException("Erro ao salvar arquivo: " + targetLink);
			             }
			         });
		         }
		        
		         

		         
		         return ResponseEntity.ok("aaaaaaaaa");
	      }
	     ActivityProgressEntity progressEntity = progressRepository.findByUserTrailAndActivity(userId, trailId, progressMinePerStatus)
	             .orElseThrow(() -> new EntityNotFoundException("Progresso não identificado linha"));
	     
	     System.out.println("""
	             ////////////////////////////////////////
	             //////////////////////////////////////// ActivityId + = """ + progressEntity.getActivity().getActivityId());

	     if (progressEntity.getActivityStatus() == StatusOfLife.ENABLE && progressEntity.getUser().getUserRole() == UserRoleEnum.ALUNO) {

	    	 Long submissionCount = submissionsRepository.countByProgress_ProgressIdAndArchiveNameIsNotNull(progressEntity.getProgressId());
	    	 Long submittedArchives = submissionsRepository.countByProgress_ProgressIdAndArchiveNameIsNull(progressEntity.getProgressId());
	         if (submissionCount >= 5 && submittedArchives >=5) {
	             throw new IllegalStateException("Limite de 10 entregues atingido para esta atividade!");
	         }

	         progressEntity.setActivityStatus(StatusOfLife.COMPLETE);
	         progressEntity.setUpdatedAt(LocalDate.now());
	         progressRepository.save(progressEntity);
	         RewardPackageEntity rewardPackageEntity = rewardRepository.findById(activityEntity.getRewardPackage().getRewardPackageId())
	            		.orElseThrow(()-> new SoftNotFoundException("Eita!, recompensa não encontrada :("));
	         ApprenticeInventoryEntity inventory = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryEquippedTrue(userId,ItemCatalogInventoryEnum.PET).orElse(null) ;

	         if(inventory!=null) {
	          	  PetEntity obtainPet = petRepository.findById(inventory.getApprenticeInventoryItemId()).orElseThrow(()-> new SoftNotFoundException("Pet não encontrado!"));
	        	  ControlApprenticePetEntity ctrlOfPet = controlApprenticePetRepository.findByApprenticeUserIdAndPetId(userId, obtainPet.getPetId()).orElseThrow(()-> new SoftNotFoundException("Controle não encontrado!"));
	        	 if(ctrlOfPet.getApprenticePetEnergyState().equals(PetStatusEnergyEnum.HAPPY)) {
	        		 
	        	 
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
	        	  if(ctrlOfPet.getApprenticePetEnergyState().equals(PetStatusEnergyEnum.HAPPY)) {
		        	  UserEntity userModifiedReward = rewardUtils.applyModifierInReward(userEntity, rewardPackageEntity, obtainPet, petOutfitEntity);
		        	  usersRepository.save(userModifiedReward);

		        	  Long LossEnergyPetCalculumn = (long) Math.round(obtainPet.getPetMaxEnergy()/10);
		        	  if(ctrlOfPet.getApprenticePetEnergy()-LossEnergyPetCalculumn< 0L) {
		            	  ctrlOfPet.setApprenticePetEnergy(0L);
		        	  }else {
		        		  ctrlOfPet.setApprenticePetEnergy(ctrlOfPet.getApprenticePetEnergy()-LossEnergyPetCalculumn);
		        	  }
		        	  
		        	  if(ctrlOfPet.getApprenticePetEnergy()<obtainPet.getPetMinEnergy()) {
		        		  ctrlOfPet.setApprenticePetEnergyState(PetStatusEnergyEnum.EXHAUSTED);
		        		  AtomicInteger counter = new AtomicInteger(0);
		      			//ApprenticeInventoryEntity apprenticeInventoryItemSkinEquipped = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryEquippedTrue(userId, ItemCatalogInventoryEnum.SKIN).orElseThrow(()->new SoftNotFoundException("Pet não permitiu acesso ao guarda roupa de novo"));

		      			String[] petIdentifiedPack = petOutfitEntity.getPetOutfitPackageSkin().split("_");
		      			obtainPet.getOutfits().forEach(outfit->{
		      				if(counter.get()>=2)return;
		      				String[] outfitParts = outfit.getPetOutfitPackageSkin().split("_");
		      				if(outfitParts[0].length()>0 && outfitParts[0].contains(petIdentifiedPack[0])) {
		      				ApprenticeInventoryEntity apprenticeOutfitsInventoryEntity = apprenticeInventoryRepository.findByApprentice_UserIdAndApprenticeInventoryTagAndApprenticeInventoryItemId(userId, ItemCatalogInventoryEnum.SKIN, outfit.getPetOutfitId()).orElseThrow(()->new SoftNotFoundException("pet se recusa a colocar a roupa :("));

		      				apprenticeOutfitsInventoryEntity.setApprenticeInventoryEquipped(ctrlOfPet.getApprenticePetEnergyState().equals(outfit.getPetOutfitSkinMode()));
		      				apprenticeInventoryRepository.save(apprenticeOutfitsInventoryEntity);
		      				 counter.incrementAndGet();
		      				}
		      			});
		        	  }
		        	  
		        	  System.out.println("MODIFICADA - - - - - Recompensa modificada "+" o xp = "+ userModifiedReward.getXp()+" e o money = " +userModifiedReward.getUserMoney()+" e a food = "+ userModifiedReward.getUserFood() );
		        	  controlApprenticePetRepository.save(ctrlOfPet);
	        	  }
	        	  }else {
	        		    userEntity.setUserFood(userEntity.getUserFood()+rewardPackageEntity.getRewardPackageFood());
	   	   	         userEntity.setUserMoney(userEntity.getUserMoney() +rewardPackageEntity.getRewardPackageMoney());
	   	   	         userEntity.setXp(userEntity.getXp() + rewardPackageEntity.getRewardPackageXp());
	   	   	         usersRepository.save(userEntity);
	        	  }

	        	  
	        	  
	         }else {
	        	    userEntity.setUserFood(userEntity.getUserFood()+rewardPackageEntity.getRewardPackageFood());
	   	         userEntity.setUserMoney(userEntity.getUserMoney() +rewardPackageEntity.getRewardPackageMoney());
	   	         userEntity.setXp(userEntity.getXp() + rewardPackageEntity.getRewardPackageXp());
	   	         usersRepository.save(userEntity);
	   	      System.out.println("NOOOOOORMAL" );
	         }
	            
	     
	       
	        
	   
	     
	         if (progressEntity.getActivityStatus() == StatusOfLife.COMPLETE) {
	        	 if (dto.getActivityFiles() != null && !dto.getActivityFiles().isEmpty()) {
	         
	             dto.getActivityFiles().forEach(targetArchive -> {
	            	 
	            	  Long checksubmissionCount = submissionsRepository.countByProgress_ProgressIdAndArchiveNameIsNotNull(progressEntityCompleted.getProgressId());
				         if (checksubmissionCount >= 5) {
				             throw new ExcededMaxDelimiter("Limite de 5 entregas atingido para esta atividade!");
				         }
	                 SubmissionEntity submissionEntity = new SubmissionEntity();
	                 submissionEntity.setProgress(progressEntity);
	                 try {
	                     makeUploadAndDownloadArchive.saveArchive(targetArchive, submissionEntity, submissionsRepository);
	                 } catch (IOException e) {
	                     throw new RuntimeException("Erro ao salvar arquivo: " + targetArchive.getOriginalFilename());
	                 }
	             
	             });}
	        	 System.err.println("TESTETEETETETETETETETETET ->");
	        	  System.out.println("NÃO ENTROU AINDAAAAA - DEBUG ! :" + dto.getActivityLinks());
	        	 if (dto.getActivityLinks() != null && !dto.getActivityLinks().isEmpty()) {
	    	         System.out.println("ENTROUUUUU PARA ENTREGAR O LINKKKKKKKKKKK!");
		             dto.getActivityLinks().forEach(targetLink -> {
		            	 
		            	  Long checksubmissionCount = submissionsRepository.countByProgress_ProgressIdAndArchiveNameIsNull(progressEntityCompleted.getProgressId());
					         if (checksubmissionCount >= 5) {
					             throw new ExcededMaxDelimiter("Limite de 5 entregas atingido para esta atividade!");
					         }
		                 SubmissionEntity submissionEntity = new SubmissionEntity();
		                 submissionEntity.setProgress(progressEntity);
		                 try {
		                	
		                	 submissionEntity.setSubmissionLink(targetLink);
			            	 submissionsRepository.save(submissionEntity);
		                 } catch (Exception e) {
		                     throw new RuntimeException("Erro ao salvar arquivo: " + targetLink);
		                 }
		             
		             });}
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


