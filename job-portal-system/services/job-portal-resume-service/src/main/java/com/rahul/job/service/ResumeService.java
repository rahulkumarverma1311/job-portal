package com.rahul.job.service;


import com.rahul.job.dto.PersonalInformationResponse;
import com.rahul.job.dto.ResumeResponse;
import com.rahul.job.model.Resume;
import com.rahul.job.payload.CreateResumeRequest;

import java.util.List;

public interface ResumeService {
    ResumeResponse createResume(Long candidateId, CreateResumeRequest req);

    ResumeResponse getResumeById(Long resumeId,Long candidateId) throws Exception;

    List<ResumeResponse> getMyResumes(Long candidateId);

    ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInformationResponse req) throws Exception;

    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception;

    ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception;

    void deleteResume(Long resumeId, Long candidateId) throws Exception;


    Resume getResumeEntityById (Long id) throws Exception;

}
