package com.calygam.back.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.DailyFlagsEntity;

@Repository
public interface DailyFlagsRepository extends JpaRepository<DailyFlagsEntity, Long> {
	Optional<DailyFlagsEntity> findByUser_userIdAndDailyFlagCreatedAt(Long userId,LocalDate dailyFlagCreatedAt);
	
	@Query(value = "SELECT CURRENT_DATE()", nativeQuery = true)
	LocalDate getDatabaseCurrentDate();
}
