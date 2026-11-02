package com.rahul.job.service;

import com.rahul.job.dto.SaveJobResponse;
import com.rahul.job.payload.SaveJobRequest;

import java.util.List;

public interface SaveJobService {

    SaveJobResponse saveJob(Long candidateId, SaveJobRequest req) throws Exception;
    void unSaveJob(Long candidateId, Long savedJobId) throws Exception;

    List<SaveJobResponse> getSavedJobs(Long candidateId);

    Boolean isSaved(Long candidateId, Long jobId);

}
