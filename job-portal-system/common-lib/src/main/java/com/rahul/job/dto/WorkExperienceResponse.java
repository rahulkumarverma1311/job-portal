package com.rahul.job.dto;

import com.rahul.job.domain.JobType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkExperienceResponse {
    private Long id;
    private String companyName;
    private String companyLogoUrl;
    private String jobTitle;
    private String location;
    private JobType employmentType;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isCurrentJob;
    private List<String>technologies;
    private Integer displayOrder;

}
