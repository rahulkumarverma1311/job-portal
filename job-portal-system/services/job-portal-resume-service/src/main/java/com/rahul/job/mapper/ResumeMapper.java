package com.rahul.job.mapper;

import com.rahul.job.dto.*;
import com.rahul.job.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeMapper {


    public static PersonalInformationResponse toPersonalInformationResponse(PersonalInfo personalInfo){
        if(personalInfo == null)return null;
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
    public static ResumeResponse toResponse(Resume resume, List<WorkExperienceResponse> workExperienceResponses,List<EducationResponse> educationResponses,List<ResumeSkillResponse>resumeSkillResponses,List<ProjectResponse> projectResponses,List<LanguageResponse>languageResponses,List<AwardResponse>awardResponses,List<CertificationResponse> certificationResponses){
        return resume == null? null: ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .title(resume.getTitle())
                .template(resume.getTemplate())
                .visibility(resume.getVisibility())
                .isDefault(resume.getIsDefault())
                .personalInfo(ResumeMapper.toPersonalInformationResponse(resume.getPersonalInfo()))
                .summary(resume.getSummary())
                .awards(awardResponses)
                .educations(educationResponses)
                .workExperiences(workExperienceResponses)
                .skills(resumeSkillResponses)
                .projects(projectResponses)
                .languages(languageResponses)
                .certifications(certificationResponses)
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
                .technologies(
                        project.getTechnologies() == null
                                ? List.of()
                                : new ArrayList<>(project.getTechnologies())
                )
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isOnGoing(project.getIsOnGoing())
                .displayOrder(project.getDisplayOrder())
                .build();
    }

    public static LanguageResponse toLanguageResponse(Language language){
        if(language == null) return null;
        return LanguageResponse.builder()
                .id(language.getId())
                .languageName(language.getLanguageName())
                .languageProficiency(language.getProficiency())
                .displayOrder(language.getDisplayOrder())
                .build();
    }


    public static AwardResponse toAwardResponse(Award award) {
        if(award == null) return null;
        return AwardResponse.builder()
                .id(award.getId())
                .title(award.getTitle())
                .issuer(award.getIssuer())
                .dateReceived(award.getDateReceived())
                .description(award.getDescription())
                .displayOrder(award.getDisplayOrder())
                .createAt(award.getCreateAt())
                .updatedAt(award.getUpdatedAt())
                .build();
    }


    public static CertificationResponse toCertificationResponse(
            Certification certification
    ) {
        if(certification == null) return null;
        return CertificationResponse.builder()
                .id(certification.getId())
                .name(certification.getName())
                .issuingOrganization(certification.getIssuingOrganization())
                .issueDate(certification.getIssueDate())
                .expirationDate(certification.getExpirationDate())
//                .credentialId(certification.getCredentialId())
//                .credentialUrl(certification.getCredentialUrl())
                .description(certification.getDescription())
                .doesNotExpire(certification.getDoesNotExpire())
                .displayOrder(certification.getDisplayOrder())
                .createAt(certification.getCreateAt())
                .updatedAt(certification.getUpdatedAt())
                .build();
    }
}
