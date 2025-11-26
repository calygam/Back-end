package com.calygam.back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calygam.back.models.SubmissionEntity;
import com.calygam.back.projections.SubmissionArchivesForTeacherProjection;
import com.calygam.back.projections.SubmissionArchivesProjection;

public interface SubmissionsRepository extends JpaRepository<SubmissionEntity, Long> {
	
	
	@Query(value = """
	        SELECT 
	        tbs.submission_archive_name AS archiveName,
	        tbs.submission_original_name AS originalName,
	        tbs.submission_id AS submissionId ,
	        tbs.submission_link AS submisionLink
	        FROM tb_submission tbs 
	        WHERE tbs.progress_id = :progressId
	        """, nativeQuery = true)
	List<SubmissionArchivesProjection> findArchivesForDownloadAcessPerProgressId(@Param("progressId") Long progressId);
	/*@Query("""
		    SELECT 
		        tbs.archiveName AS submissionArchiveName,
		        tbs.originalName AS submissionOriginalName,
		        tbs.submissionId AS submissionId,
		        tbpu.userName AS userName,
		        tbpu.archiveName AS userArchiveName
		    FROM SubmissionEntity tbs
		    JOIN tbs.progress tbp
		    JOIN tbp.user tbpu
		    WHERE tbp.id = :progressId
		    """)
		List<SubmissionArchivesForTeacherProjection> findArchivesForDownloadAcessPerProgressIdAndPhoto(
		    @Param("progressId") Long progressId
		);*/
	
	@Query("""
		    SELECT 
		        tbs.archiveName AS submissionArchiveName,
		        tbs.originalName AS submissionOriginalName,
		        tbs.submissionId AS submissionId,
		        tbpu.userId AS userId,
		        tbpu.userName AS userName,
		        tbpu.archiveName AS userArchiveName,
		        tbs.submissionLink AS submissionLink
		    FROM SubmissionEntity tbs
		    JOIN tbs.progress tbp
		    JOIN tbp.user tbpu
		    WHERE tbp.activity.activityId = :activityId
		    """)
		List<SubmissionArchivesForTeacherProjection> findArchivesForDownloadAcessPerProgressIdAndPhoto(
		    @Param("activityId") Long activityId
		);
	@Query(value="""
			
			SELECT * FROM tb_submission s WHERE s.progress_id = :progressId""",nativeQuery=true)
	Optional<SubmissionEntity> findSubmmitedArchivesByProgressId(@Param("progressId") Long progressId);
	
	@Query("SELECT s FROM SubmissionEntity s WHERE s.originalName = :originalName")
	Optional<SubmissionEntity> findByOriginalName(@Param("originalName") String originalName);
	
	
	Long countByProgress_ProgressIdAndArchiveNameIsNotNull(Long progressId);
	Long countByProgress_ProgressIdAndArchiveNameIsNull(Long progressId);
}
