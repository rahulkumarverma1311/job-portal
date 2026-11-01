package com.rahul.job.service;

import com.rahul.job.domain.ApplicationStatus;
import com.rahul.job.dto.ApplicationResponse;
import com.rahul.job.model.Application;
import com.rahul.job.payload.AddApplicationRequest;
import com.rahul.job.payload.CompanyApplicationFilterRequest;
import com.rahul.job.payload.WithdrawApplicationRequest;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse createApplication(Long candidateId, AddApplicationRequest req) throws Exception;

    ApplicationResponse getApplicationById(Long id) throws Exception;

    List<ApplicationResponse> getMyApplication(Long candidateId);

    List<ApplicationResponse> getApplicationForCompany(Long userId, CompanyApplicationFilterRequest req);

    List<ApplicationResponse> getApplicationForJob(Long jobId);

    ApplicationResponse updateApplicationStatus(Long applicationId, Long employerId,ApplicationStatus status) throws Exception;

    ApplicationResponse withdrawnApplication(Long applicationId, Long candidateId, WithdrawApplicationRequest req) throws Exception;

    ApplicationResponse toggleStar(Long applicationId,Long employerId) throws Exception;

    Application getApplicationEntity(Long id) throws Exception;

    void deleteApplication(Long applicationId,Long candidateId) throws Exception;




}
