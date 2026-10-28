package com.rahul.job.service;

import com.rahul.job.domain.ApplicationStatus;
import com.rahul.job.dto.ApplicationResponse;
import com.rahul.job.model.Application;
import com.rahul.job.payload.AddApplicationRequest;
import com.rahul.job.payload.WithdrawApplicationRequest;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse createApplication(Long candidateId, AddApplicationRequest req);

    ApplicationResponse getApplicationById(Long id);

    List<ApplicationResponse> getMyApplication(Long candidateId);

    List<ApplicationResponse> getApplicationForCompany(Long companyId);

    List<ApplicationResponse> getApplicationForJob(Long jobId);

    ApplicationResponse updateApplicationStatus(Long applicationId, Long employerId,ApplicationStatus status);

    ApplicationResponse withdrawnApplication(Long applicationId, Long candidateId, WithdrawApplicationRequest req);

    ApplicationResponse toggleStar(Long applicationId,Long employerId);

    Application getApplicationEntity(Long id);

    void deleteApplication(Long applicationId,Long candidateId);




}
