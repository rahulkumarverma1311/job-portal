package com.rahul.job.mapper;

import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.dto.JobResponse;
import com.rahul.job.dto.JobSkillResponse;
import com.rahul.job.dto.JobTagResponse;
import com.rahul.job.model.Job;
import com.rahul.job.model.embedable.JobLocation;
import com.rahul.job.model.embedable.SalaryRange;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class JobMapper {


    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){
        JobLocation loc = job.getLocation();
        SalaryRange sal = job.getSalaryRange();

        Set<JobSkillResponse>jobResponses  = (job.getSkills() == null) ? Collections.emptySet() : job.getSkills().stream().map(JobSkillMapper::convertToResponse).collect(Collectors.toSet());
        Set<JobTagResponse>jobTagResponses = job.getJobTags() == null ? Collections.emptySet() : job.getJobTags().stream().map(JobTagMapper::convertToJobTagResponse).collect(Collectors.toSet());
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
                .category(JobCategoryMapper.toJobCategoryResponse(job.getJobCategory(),false))
                .skills(jobResponses)
                .tags(jobTagResponses)
                .address(loc != null ? loc.getAddress() : null)
                .city(loc != null ? loc.getCity() : null)
                .state(loc != null ? loc.getState() : null)
                .country(loc != null ? loc.getCountry() : null)
                .zipCode(loc != null ? loc.getZipCode() : null)

                // Salary
                .minSalary(sal != null ? sal.getMinSalary() : null)
                .maxSalary(sal != null ? sal.getMaxSalary() : null)
//                .currency(sal != null ? sal.getCurrency() : null)
//                .salaryPeriod(sal != null ? sal.getPeriod() : null)
//                .salaryNegotiable(sal != null ? sal.getNegotiable() : null)
//                .salaryDisclosed(sal != null ? sal.getDisclosed() : null)
                // Classification
                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .status(job.getJobStatus())

// Posting
                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadLine())
                .expiresAt(job.getExpiresAt())
                .active(job.getActive())


// Timestamps
                .createdAt(job.getCreateAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closedAt(job.getCloseAt())
                .build();
    }
}
