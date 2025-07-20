package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.ActivityEntity;
import com.calygam.back.projections.ActivityProjection;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
	
	@Query("""
			SELECT 
				atv.activityId AS activityId,
				atv.activityName AS activityName,
				atv.activityDescription AS activityDescription,
				atv.activityDifficulty AS activityDifficulty,
				atv.activityStatus AS activityStatus,
				atv.activityCreatedAt AS activityCreatedAt,
				atv.activityUpdatedAt AS activityUpdatedAt
			FROM ActivityEntity atv
			WHERE atv.trail.id = :trailId
				
			""")
	List<ActivityProjection> findActivitiesPerTargetTrailId(@Param("trailId")Long trailId);
	
	@Query("""
			SELECT 
				atv.activityId AS activityId,
				atv.activityName AS activityName,
				atv.activityDescription AS activityDescription,
				atv.activityDifficulty AS activityDifficulty,
				atv.activityStatus AS activityStatus,
				atv.activityCreatedAt AS activityCreatedAt,
				atv.activityUpdatedAt AS activityUpdatedAt
			FROM ActivityEntity atv
			WHERE atv.trail.id = :trailId AND atv.activityId = :activityId
				
			""")
	ActivityProjection findActivityDetails(@Param("trailId") Long trailId,@Param("activityId") Long activityId);
	
	@Query("""
			SELECT COUNT(atv) 
			FROM ActivityEntity atv
			WHERE atv.trail.id = :trailId
			"""
			)
	Long countTotalActivitiesByTrailId(@Param("trailId")Long trailId);
	
	@Query("""
			SELECT atv FROM ActivityEntity atv WHERE atv.trail.trailId = :trailId AND atv.activityId = :activityId
			""")
	Optional<ActivityEntity> findActivityByTrailIdAndActivityId(@Param("activityId") Long activityId, @Param("trailId") Long trailId);
	
}
