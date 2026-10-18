package com.rahul.job.service;

import com.rahul.job.dto.JobRequest;
import com.rahul.job.dto.JobResponse;
import com.rahul.job.payload.JobSearchRequest;

import java.util.List;

public interface JobService {


    JobResponse createJob(Long employeeId, JobRequest request) throws Exception;

    JobResponse getJobById(Long id) throws Exception;

    JobResponse updateJob(Long jobId ,Long employeeId, JobRequest request) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest request);

    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse publishJob(Long jobId,Long employeeId) throws Exception;

    JobResponse closeJob(Long jobId,Long employeeId) throws Exception;

    void deleteJob(Long jobId,Long employeeId) throws Exception;

    List<JobResponse> getAllJobsAdmin();



}
