package com.rahul.job.mapper;

import com.rahul.job.dto.JobTagResponse;
import com.rahul.job.model.JobTag;

public class JobTagMapper {

    public static JobTagResponse convertToJobTagResponse(JobTag jobTag){
        return JobTagResponse.builder()
                .name(jobTag.getName())
                .id(jobTag.getId())
                .slug(jobTag.getSlug())
                .build();
    }
}
