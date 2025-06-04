package com.calygam.back.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calygam.back.models.ActivityProgressEntity;
import com.calygam.back.projections.ProgressAssignProjection;

@Repository
public interface ProgressRepository extends JpaRepository<ActivityProgressEntity, Long> {
	@Query("SELECT p.progressId AS progressId, p.user.id AS userId, p.trail.id AS trailId, p.activity.id AS activityId, " +
		       "p.trailStatus AS trailStatus, p.activityStatus AS activityStatus, " +
		       "p.createdAt AS createdAt, p.updatedAt AS updatedAt, p.unlockedActivities AS unlockedActivities " +
		       "FROM ActivityProgressEntity p " +
		       "WHERE p.user.id = :userId AND p.trail.id = :trailId")
		List<ProgressAssignProjection> findProgressByUserIdAndTrailId(Long userId, Long trailId);
}
