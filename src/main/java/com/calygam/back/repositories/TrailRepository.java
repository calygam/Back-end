package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calygam.back.dtos.TrailDTO;
import com.calygam.back.models.TrailEntity;
import com.calygam.back.projections.ProgressBarTrailProjection;

public interface TrailRepository extends JpaRepository<TrailEntity, Long> {
	
	
	@Query("""
			 SELECT NEW com.calygam.back.dtos.TrailDTO(
				  t.trailId, t.trailName, t.archiveName,
				 	t.trailDescription,
			t.trailCreatedDate,t.trailUpdatedDate,t.trailVacancy,
			t.trailVacancies,t.user.userId)
			 FROM TrailEntity t WHERE  t.trailName = :trailName
			""")
	
	TrailDTO findExistentTrail(@Param("trailName")String trailName);
	@Query("""
		    SELECT t
		    FROM TrailEntity t
		    WHERE NOT EXISTS (
		        SELECT 1
		        FROM ActivityProgressEntity p
		        WHERE p.trail = t AND p.user.userId = :userId
		    )
		""")
		List<TrailEntity> foundTrailsByNotExistsProgress(@Param("userId") Long userId);
    
	@Query("""
			  SELECT DISTINCT t
			  FROM TrailEntity t
			  LEFT JOIN FETCH t.activities 
			  LEFT JOIN t.progress prog 
			  WHERE prog.user.userId = :userId
			""")
			List<TrailEntity> foundTrailsByExistentProgress(@Param("userId") Long userId);
    
    @Query(value = """
    		SELECT 
    		 	t.trail_id,
				SUM(CASE WHEN prog.activity_status=2 THEN 1 ELSE 0 END) AS totalActivitiesCompleted, 
				COUNT(prog.progress_id) AS totalActivities FROM tb_trail t 
			RIGHT JOIN tb_trail_x_activity_progress prog ON t.trail_id = prog.trail_id WHERE prog.user_id = :userId 
			GROUP BY  t.trail_id 
    		""",nativeQuery=true)
    List<ProgressBarTrailProjection> getProgressToTrailBar(@Param("userId") Long userId);
   
    @Query("""
  	      SELECT DISTINCT t
  	      FROM TrailEntity t
  	      LEFT JOIN FETCH t.activities WHERE  t.user.userId = :userId
  	    """)
		List<TrailDTO> findAllTrailsWithActivitiesPerTeacher(@Param("userId") Long userId);
    
    Optional<TrailEntity> findById(Long trailId);
    
    
}
