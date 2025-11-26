package com.calygam.back.projections;

public interface SubmissionArchivesForTeacherProjection {
	 
     String getSubmissionArchiveName();
     String getSubmissionOriginalName();
     Long 	getSubmissionId();
     Long getUserId();
     String getUserName();
     String getUserArchiveName();
     String getSubmissionLink();

}
