package com.calygam.back.projections;

import java.util.List;

public interface SubmissionArchivesProjection {
    String getArchiveName();
    String getOriginalName();
    Long getSubmissionId();
    String getSubmisionLink();
}
