package com.calygam.back.services;

import java.time.LocalDate;
import java.time.ZoneId;

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
		LocalDate flagsInToday = dailyFlagsRepository.getDatabaseCurrentDate();
		System.out.println("""
				////////////////////////////////////
				///////////////////////////////////
				///////////////////////////////////
				///////////////////////////////////
				////////////////////////////////////
				///////////////////////////////////
				""");
		System.out.println("VEEEEEEEEM ISSSSSO = + = " + flagsInToday );
		return 
		dailyFlagsRepository
		.findByUser_userIdAndDailyFlagCreatedAt(user.getUserId(), flagsInToday)
		.orElseGet(()->{
			DailyFlagsEntity flagsForToday = new DailyFlagsEntity();
			flagsForToday.setUser(user);
			flagsForToday.setDailyFlagCreatedAt(dailyFlagsRepository.getDatabaseCurrentDate());
			flagsForToday.setUserFlags(3L);
			return dailyFlagsRepository.save(flagsForToday);
		});
		
	}
}
