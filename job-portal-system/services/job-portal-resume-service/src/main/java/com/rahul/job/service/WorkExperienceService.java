package com.rahul.job.service;

import com.rahul.job.dto.WorkExperienceResponse;
import com.rahul.job.model.WorkExperience;
import com.rahul.job.payload.AddWorkExperienceRequest;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest req) throws Exception;
    List<WorkExperienceResponse> getWorkExperiences(Long resumeId);
    WorkExperienceResponse updateWorkExperience(Long resumeId,Long candidateId,Long workExperienceId,AddWorkExperienceRequest req) throws Exception;
    void deleteWorkExperience(Long resumeId,Long workExperiences,Long candidateId) throws Exception;

    WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception;
}
