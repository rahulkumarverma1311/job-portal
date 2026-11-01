package com.rahul.job.payload;

import com.rahul.job.domain.AiShortListStatus;
import com.rahul.job.domain.ApplicationStatus;
import lombok.Data;

@Data

public class CompanyApplicationFilterRequest {


    private Long jobId;
    private ApplicationStatus status;
    private Boolean isStarred;
    private AiShortListStatus aiShortListStatus;
    private Integer minAiScore;
    private String sortBy;
}
