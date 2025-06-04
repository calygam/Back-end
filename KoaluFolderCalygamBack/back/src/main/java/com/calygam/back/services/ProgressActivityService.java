package com.calygam.back.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.ActivityProgressDTO;
import com.calygam.back.dtos.ActivityProgressResponseDTO;
import com.calygam.back.mappers.ActivityProgressMapper;
import com.calygam.back.models.ActivityProgressEntity;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.projections.ProgressAssignProjection;
import com.calygam.back.repositories.ProgressRepository;
import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.repositories.UsersRepository;
import com.calygam.back.utils.GenerateProgress;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProgressActivityService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TrailRepository trailRepository;
	
	@Autowired
	private GenerateProgress generateProgress;
	
	@Autowired
	private ProgressRepository progressRepository;
	
	@Autowired
	private ActivityProgressMapper activityProgressMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<ActivityProgressResponseDTO> progressAssignStudent(String trailPassword,Long userId, Long trailId)  {
	    UserEntity userEntity = usersRepository.findEntityByUserId(userId)
	        .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

	    TrailEntity trailEntity = trailRepository.findById(trailId)
	        .orElseThrow(() -> new EntityNotFoundException("Trilha não encontrada"));
	    AtomicInteger index = new AtomicInteger(0);
	    
	    if(passwordEncoder.matches(trailPassword, trailEntity.getTrailPassword())) {
	    	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	  

	    List<ActivityProgressEntity> progressList = trailEntity.getActivities().stream()
	        .map(activity -> {
	            boolean isFirst = index.getAndIncrement() == 0;

	            ActivityProgressDTO dto = new ActivityProgressDTO();
	            dto.setUser(userEntity);
	            dto.setTrail(trailEntity);
	            dto.setActivity(activity);

	            return generateProgress.assignProgress(dto, isFirst);
	        })
	        .collect(Collectors.toList());

	     progressRepository.saveAll(progressList);

	    List<ProgressAssignProjection> progressProjections = progressRepository
	            .findProgressByUserIdAndTrailId(userId, trailId);

	
	        return progressProjections.stream()
	            .map(p-> activityProgressMapper.convertToDTO(p))
	            .collect(Collectors.toList());
	    }else {
	    	System.out.println("AOOOOOOOOOEOEOEOOEEEEE");
	    	throw new BadCredentialsException("Senha incorreta para acesso à trilha.");
	    }
	}
	
}
