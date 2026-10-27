package com.rahul.job.service;

import com.rahul.job.dto.EducationResponse;
import com.rahul.job.payload.EducationCreateRequest;

import java.util.List;

public interface EducationService {

    EducationResponse addEducation(Long resumeId,Long candidateId,EducationCreateRequest req) throws Exception;

    List<EducationResponse> getEducations(Long resumeId);

    EducationResponse updateEducation(Long educationId,Long resumeId,Long candidateId,EducationCreateRequest req) throws Exception;

    void deleteEducation(Long resumeId,Long candidateId,Long educationId) throws Exception;

}
