package com.rahul.job.mapper;

import com.rahul.job.dto.WorkExperienceResponse;
import com.rahul.job.model.WorkExperience;

public class WorkExperienceMapper {

    public static WorkExperienceResponse toResponse(WorkExperience exp){
        if(exp == null) return null;
        return WorkExperienceResponse.builder()
                .id(exp.getId())
                .companyName(exp.getCompanyName())
                .companyLogoUrl(exp.getCompanyLogoUrl())
                .jobTitle(exp.getJobTitle())
                .description(exp.getDescription())
                .displayOrder(exp.getDisplayOrder())
                .employmentType(exp.getEmploymentType())
                .endDate(exp.getEndDate())
                .startDate(exp.getStartDate())
                .isCurrentJob(exp.getIsCurrentJob())
                .technologies(exp.getTechnologies())
                .location(exp.getLocation())
                .build();
    }
}
