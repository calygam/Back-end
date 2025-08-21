package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calygam.back.models.SubmissionEntity;
import com.calygam.back.projections.SubmissionArchivesProjection;

public interface SubmissionsRepository extends JpaRepository<SubmissionEntity, Long> {
	
	
	@Query(value = """
	        SELECT 
	        tbs.submission_archive_name AS archiveName,
	        tbs.submission_original_name AS originalName,
	        tbs.submission_id AS submissionId 
	        FROM tb_submission tbs 
	        WHERE tbs.progress_id = :progressId
	        """, nativeQuery = true)
	List<SubmissionArchivesProjection> findArchivesForDownloadAcessPerProgressId(@Param("progressId") Long progressId);
	
	@Query(value="""
			
			SELECT * FROM tb_submission s WHERE s.progress_id = :progressId""",nativeQuery=true)
	Optional<SubmissionEntity> findSubmmitedArchivesByProgressId(@Param("progressId") Long progressId);
	
	@Query("SELECT s FROM SubmissionEntity s WHERE s.originalName = :originalName")
	Optional<SubmissionEntity> findByOriginalName(@Param("originalName") String originalName);
	
	@Query(value = """
		    SELECT COUNT(*) FROM tb_submission s WHERE s.progress_id = :progressId
		    """, nativeQuery = true)
		Long countSubmissionsByProgressId(@Param("progressId") Long progressId);
}
