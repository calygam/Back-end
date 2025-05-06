package com.calygam.back.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calygam.back.dtos.TrailDTO;
import com.calygam.back.models.TrailEntity;

public interface TrailRepository extends JpaRepository<TrailEntity, Long> {
	
	
	@Query("""
			 SELECT NEW com.calygam.back.dtos.TrailDTO(
				  t.trailId, t.trailName, t.archiveName,
				 	t.trailDescription,t.trailPrice,
			t.trailCreatedDate,t.trailUpdatedDate,t.trailVacancy,
			t.trailVacancies,t.user.userId)
			 FROM TrailEntity t WHERE  t.trailName = :trailName AND t.user.userId = :userId 
			""")
	
	TrailDTO findExistentTrail(@Param("userId")Long userId,@Param("trailName")String trailName);
    @Query("""
    	      SELECT DISTINCT t
    	      FROM TrailEntity t
    	      LEFT JOIN FETCH t.activities
    	    """)
		List<TrailDTO> findAllTrailsWithActivities();
}
