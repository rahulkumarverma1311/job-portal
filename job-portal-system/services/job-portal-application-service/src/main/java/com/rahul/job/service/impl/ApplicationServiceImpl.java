package com.rahul.job.service.impl;

import com.rahul.job.domain.ApplicationStatus;
import com.rahul.job.dto.ApplicationResponse;
import com.rahul.job.model.Application;
import com.rahul.job.payload.AddApplicationRequest;
import com.rahul.job.payload.WithdrawApplicationRequest;
import com.rahul.job.repository.ApplicationRepository;
import com.rahul.job.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {


    private final ApplicationRepository applicationRepository;


    @Override
    public ApplicationResponse createApplication(Long candidateId, AddApplicationRequest req) {
        return null;
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {
        return null;
    }

    @Override
    public List<ApplicationResponse> getMyApplication(Long candidateId) {
        return List.of();
    }

    @Override
    public List<ApplicationResponse> getApplicationForCompany(Long companyId) {
        return List.of();
    }

    @Override
    public List<ApplicationResponse> getApplicationForJob(Long jobId) {
        return List.of();
    }

    @Override
    public ApplicationResponse updateApplicationStatus(Long applicationId, Long employerId, ApplicationStatus status) {
        return null;
    }

    @Override
    public ApplicationResponse withdrawnApplication(Long applicationId, Long candidateId, WithdrawApplicationRequest req) {
        return null;
    }

    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) {
        return null;
    }

    @Override
    public Application getApplicationEntity(Long id) {
        return null;
    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId) {

    }
}
