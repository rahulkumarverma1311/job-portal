package com.rahul.job.mapper;

import com.rahul.job.dto.PersonalInformationResponse;
import com.rahul.job.dto.ResumeResponse;
import com.rahul.job.model.PersonalInfo;
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
}
