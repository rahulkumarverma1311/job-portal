package com.rahul.job.mapper;

import com.rahul.job.dto.EducationResponse;
import com.rahul.job.dto.PersonalInformationResponse;
import com.rahul.job.dto.ProjectResponse;
import com.rahul.job.dto.ResumeResponse;
import com.rahul.job.model.Education;
import com.rahul.job.model.PersonalInfo;
import com.rahul.job.model.Project;
import com.rahul.job.model.Resume;

public class ResumeMapper {


    public static PersonalInformationResponse toPersonalInformationResponse(PersonalInfo personalInfo){
        return PersonalInformationResponse.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .headline(personalInfo.getHeadline())
                .email(personalInfo.getEmail())
                .phoneNumber(personalInfo.getPhoneNumber())
                .city(personalInfo.getCity())
                .country(personalInfo.getCountry())
                .githubUrl(personalInfo.getGithubUrl())
                .linkedinUrl(personalInfo.getLinkedinUrl())
                .portfolioUrl(personalInfo.getPortfolioUrl())
                .websiteUrl(personalInfo.getWebsiteUrl())
                .build();
    }
    public static ResumeResponse toResponse(Resume resume){
        return resume == null? null: ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .title(resume.getTitle())
                .template(resume.getTemplate())
                .visibility(resume.getVisibility())
                .isDefault(resume.getIsDefault())
                .personalInfo(ResumeMapper.toPersonalInformationResponse(resume.getPersonalInfo()))
                .summary(resume.getSummary())
                .completionScore(resume.getCompletionScore())
                .createdAt(resume.getCreateAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }


    public static EducationResponse toEducationResponse(Education edu){
        if(edu == null) return null;
        return EducationResponse.builder()
                .id(edu.getId())
                .institutionsName(edu.getInstitutionsName())
                .degree(edu.getDegree())
                .fieldOfStudy(edu.getFieldOfStudy())
                .grade(edu.getGrade())
                .startDate(edu.getStartDate())
                .endDate(edu.getEndDate())
                .isCurrentlyStudying(edu.getIsCurrentlyStudying())
                .description(edu.getDescription())
                .displayOrder(edu.getDisplayOrder())
                .build();
    }


    public static ProjectResponse toProjectResponse(Project project){
        if(project == null) return null;
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .descriptions(project.getDescriptions())
                .technologies(project.getTechnologies())
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isOnGoing(project.getIsOnGoing())
                .displayOrder(project.getDisplayOrder())
                .build();
    }
}
