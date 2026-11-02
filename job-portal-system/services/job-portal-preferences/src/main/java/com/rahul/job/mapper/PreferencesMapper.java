package com.rahul.job.mapper;

import com.rahul.job.dto.SaveJobResponse;
import com.rahul.job.model.SavedJob;

public class PreferencesMapper {

    public static SaveJobResponse toSaveJobResponse(SavedJob job){
        if(job == null) return null;
        return SaveJobResponse.builder()
                .id(job.getId())
                .jobId(job.getJobId())
                .candidateID(job.getCandidateId())
                .jobId(job.getJobId())
                .savedAt(job.getSavedAt())
                .build();
    }
}
