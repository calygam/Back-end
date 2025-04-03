package com.calygam.back.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.TrailDTO;
import com.calygam.back.exceptions.UserAlreadExistsException;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.repositories.UsersRepository;

@Service
public class TrailService {
	
	@Autowired
	TrailRepository trailRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	public TrailDTO createNewTrail(Long userId,TrailDTO trailDTO) {
		
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
		
		UserEntity userEntity = usersRepository.getReferenceById(userId);
		
		trailEntity.setUser(userEntity);
		
		trailEntity = trailRepository.save(trailEntity);
		
		return new TrailDTO(trailEntity);
	}
	
	public List<TrailDTO> ReadAllTrails() {
		
		List<TrailDTO> trailDTOList = trailRepository.findAllTrails();
		
		return trailDTOList;
	}
}
