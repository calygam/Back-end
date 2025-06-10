package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.ActivityProgressEntity;
import com.calygam.back.projections.ActivityProgressProjection;
import com.calygam.back.projections.ProgressAssignProjection;

@Repository
public interface ProgressRepository extends JpaRepository<ActivityProgressEntity, Long> {
	@Query("SELECT p.progressId AS progressId, p.user.id AS userId, p.trail.id AS trailId, p.activity.id AS activityId, " +
		       "p.trailStatus AS trailStatus, p.activityStatus AS activityStatus, " +
		       "p.createdAt AS createdAt, p.updatedAt AS updatedAt, p.unlockedActivities AS unlockedActivities " +
		       "FROM ActivityProgressEntity p " +
		       "WHERE p.user.id = :userId AND p.trail.id = :trailId")
		List<ProgressAssignProjection> findProgressByUserIdAndTrailId(Long userId, Long trailId);
	
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
	           "FROM ActivityProgressEntity p " +
	           "WHERE p.user.id = :userId AND p.trail.id = :trailId")
	    boolean existsByUserIdAndTrailId(Long userId, Long trailId);
	
	
	@Query(value = """
			SELECT COUNT(*) FROM tb_trail_x_activity_progress p 
			WHERE p.activity_status = 2 
			AND p.user_id = :userId 
			AND p.trail_id = :trailId
			 
			""",nativeQuery=true)
	Long countTotalActivitiesCompleted(Long userId, Long trailId);
	
	 @Query(value = """
		        SELECT 
		            p.progress_id AS progressId,
		            p.activity_id AS activityId,
		            p.trail_id AS trailId,
		            p.activity_status AS activityStatus,
		            atv.activity_name AS activityName,
		              atv.activity_description AS activityDescription,
		            atv.activity_difficulty AS activityDifficulty,
		            atv.activity_price AS activityPrice
		        FROM tb_trail_x_activity_progress p
		        INNER JOIN tb_activities atv ON p.activity_id = atv.activity_id
		        WHERE p.user_id = :userId
		          AND p.trail_id = :trailId
		          AND p.activity_status = 0
		        ORDER BY p.activity_id DESC
		        LIMIT 1
		        """, nativeQuery = true)
		    Optional<ActivityProgressProjection> findMostRecentActivityWithProgress(
		        @Param("userId") Long userId,
		        @Param("trailId") Long trailId
		    );
	
	
}
