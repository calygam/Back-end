package com.calygam.back.services;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calygam.back.models.DailyFlagsEntity;
import com.calygam.back.models.UserEntity;
import com.calygam.back.repositories.DailyFlagsRepository;

@Service
public class DailyFlagsService {
	@Autowired
	private DailyFlagsRepository dailyFlagsRepository;
	
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
		flagsForToday.setUserFlags(4L);
		return dailyFlagsRepository.save(flagsForToday);
		}
		
		
	}
}
