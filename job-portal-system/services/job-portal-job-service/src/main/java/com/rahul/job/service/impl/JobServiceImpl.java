package com.rahul.job.service.impl;

import com.rahul.job.domain.JobStatus;
import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.dto.JobRequest;
import com.rahul.job.dto.JobResponse;
import com.rahul.job.mapper.JobMapper;
import com.rahul.job.model.Job;
import com.rahul.job.model.JobCategory;
import com.rahul.job.model.JobSkill;
import com.rahul.job.model.JobTag;
import com.rahul.job.model.embedable.JobLocation;
import com.rahul.job.model.embedable.SalaryRange;
import com.rahul.job.payload.JobSearchRequest;
import com.rahul.job.repository.JobRepository;
import com.rahul.job.repository.JobSpecification;
import com.rahul.job.service.JobCategoryService;
import com.rahul.job.service.JobService;
import com.rahul.job.service.JobSkillService;
import com.rahul.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobCategoryService jobCategoryService;
    private final JobSkillService jobSkillService;
    private final JobTagService jobTagService;
    @Override
    public JobResponse createJob(Long employeeId, JobRequest req) throws Exception {
        // todo: fetch company by employeeId
        JobCategory jobCategory = jobCategoryService.getCategoryEntityById(req.getCategoryId());
        Set<JobSkill> skills  = req.getSkillIds() != null ? jobSkillService.getSkillsByIds(req.getSkillIds()) : Collections.emptySet();
        Set<JobTag>tags = req.getTagIds() != null? jobTagService.getJobTagEntitiesByIds(req.getTagIds()):Collections.emptySet();
        Long companyId = 1L;
        Job job = Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .requirements(req.getRequirements())
                .responsibilities(req.getResponsibilities())
                .benefits(req.getBenefits())
                .companyId(companyId)
                .employeeId(employeeId)
                .jobCategory(jobCategory)
                .skills(skills)
                .jobTags(tags)
                .location(buildLocation(req))
                .salaryRange(buildSalaryRange(req))
                .jobType(req.getJobType())
                .workMode(req.getWorkMode())
                .experienceLevel(req.getExperienceLevel())
                .openings(req.getOpenings() != null ? req.getOpenings() : 1)
                .applicationDeadLine(req.getApplicationDeadline())
                .expiresAt(req.getExpiresAt())
                .active(true)
                .jobStatus(JobStatus.DRAFT)
                .build();

        Job savedJob = jobRepository.save(job);

        return converToResponse(savedJob);
    }


    @Override
    public JobResponse getJobById(Long id) throws Exception {
        Job job = jobRepository.findById(id).orElseThrow(() -> new Exception("job not found with id:- " + id));
        return converToResponse(job);
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employeeId, JobRequest req) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("job not found with id:- " + jobId));
        assertEmployer(job, employeeId);

        JobCategory jobCategory = jobCategoryService.getCategoryEntityById(req.getCategoryId());
        Set<JobSkill> skills  = req.getSkillIds() != null ? jobSkillService.getSkillsByIds(req.getSkillIds()) : Collections.emptySet();
        Set<JobTag>tags = req.getTagIds() != null? jobTagService.getJobTagEntitiesByIds(req.getTagIds()):Collections.emptySet();
        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setRequirements(req.getRequirements());
        job.setResponsibilities(req.getResponsibilities());
        job.setBenefits(req.getBenefits());

//        TODO: category not implemented
        job.setJobCategory(jobCategory);
        job.setSkills(skills);
        job.setJobTags(tags);

        job.setLocation(buildLocation(req));
        job.setSalaryRange(buildSalaryRange(req));
        job.setJobType(req.getJobType());
        job.setWorkMode(req.getWorkMode());
        job.setExperienceLevel(req.getExperienceLevel());

        job.setOpenings(
                req.getOpenings() != null
                        ? req.getOpenings()
                        : job.getOpenings()
        );

        job.setApplicationDeadLine(req.getApplicationDeadline());
        job.setExpiresAt(req.getExpiresAt());
        return converToResponse(jobRepository.save(job));
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepository.findAll(JobSpecification.build(request));
        return jobs.stream().map(job -> converToResponse(job)).collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream().map(job -> converToResponse(job)).collect(Collectors.toList());
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employeeId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("job not found with id:- " + jobId));
        assertEmployer(job, employeeId);
        if (job.getJobStatus() == JobStatus.CLOSED || job.getJobStatus() == JobStatus.EXPIRED) {
            throw new Exception("Job is Expired or Close");
        }
        job.setJobStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);
        return converToResponse(jobRepository.save(job));
    }


    @Override
    public JobResponse closeJob(Long jobId, Long employeeId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("job not found with id:- " + jobId));
        assertEmployer(job, employeeId);
        if (job.getJobStatus() == JobStatus.CLOSED || job.getJobStatus() == JobStatus.EXPIRED) {
            throw new Exception("Job is Already Close");
        }
        job.setJobStatus(JobStatus.CLOSED);
        job.setCloseAt(LocalDateTime.now());
        job.setActive(false);
        return converToResponse(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long jobId, Long employeeId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("job not found with id:- " + jobId));
        assertEmployer(job, employeeId);
        jobRepository.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return jobRepository.findAll().stream().map(this::converToResponse).collect(Collectors.toList());
    }


    private JobResponse converToResponse(Job savedJob) {

        // todo: fetch company response
        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(savedJob.getCompanyId())
                .build();
        return JobMapper.toResponse(savedJob, companyResponse);

    }

    private SalaryRange buildSalaryRange(JobRequest req) {
        return SalaryRange.builder()
                .minSalary(req.getMinSalary())
                .maxSalary(req.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest req) {
        return JobLocation.builder()
                .address(req.getAddress())
                .city(req.getCity())
                .state(req.getState())
                .country(req.getCountry())
                .zipCode(req.getZipCode())
                .build();

    }

    private void assertEmployer(Job job, Long employeeId) throws Exception {
        if (!job.getEmployeeId().equals(employeeId)) {
            throw new Exception("You are not the employer who posted this job");
        }
    }
}
