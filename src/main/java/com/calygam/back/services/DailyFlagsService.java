package com.calygam.back.services;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.dtos.FlagsActivityDTO;
import com.calygam.back.exceptions.SoftNotFoundException;
import com.calygam.back.models.DailyFlagsEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.DailyFlagsRepository;
import com.calygam.back.repositories.UsersRepository;

@Service
public class DailyFlagsService {
	@Autowired
	private DailyFlagsRepository dailyFlagsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	//caio<- vamos gerar as bandeiras que o usuário vai ter no dia
	public DailyFlagsEntity collectOrGenerateFlags(UserEntity user) {
		LocalDateTime targetHours = dailyFlagsRepository.getDatabaseCurrentTimeStamp();
		DailyFlagsEntity flagsOfUser =  dailyFlagsRepository
		.findTopByUser_userIdOrderByDailyFlagCreatedAtDesc(user.getUserId()).orElse(null);
		
		if(flagsOfUser!=null && targetHours.isBefore(flagsOfUser.getDailyFlagCreatedAt().plus(Duration.ofMinutes(100)))) {
			return flagsOfUser;
		}else {
	

		DailyFlagsEntity flagsForToday = new DailyFlagsEntity();
		flagsForToday.setUser(user);
		flagsForToday.setDailyFlagCreatedAt(dailyFlagsRepository.getDatabaseCurrentTimeStamp());
		flagsForToday.setUserFlags(12L);
		return dailyFlagsRepository.save(flagsForToday);
		}
		
		
	}
	
	public FlagsActivityDTO getFlagsAndTimer(Long userEntity) {
		UserEntity user = usersRepository.findById(userEntity).orElseThrow(()-> new SoftNotFoundException("Usuário não encontrado"));
	     DailyFlagsEntity tokenEntity = this.collectOrGenerateFlags(user); 
		LocalDateTime targetHours = dailyFlagsRepository.getDatabaseCurrentTimeStamp();
		DailyFlagsEntity flagsOfUser =  dailyFlagsRepository
		.findTopByUser_userIdOrderByDailyFlagCreatedAtDesc(user.getUserId()).orElse(null);
		Duration timerRegenFlags = Duration.between(targetHours, tokenEntity.getDailyFlagCreatedAt().plus(Duration.ofMinutes(100)));
		if(flagsOfUser.getUserFlags()<=0) {
			return new FlagsActivityDTO(flagsOfUser.getUserFlags(), timerRegenFlags.getSeconds());
		}
		return new FlagsActivityDTO(flagsOfUser.getUserFlags(), 0L);
	}
}
