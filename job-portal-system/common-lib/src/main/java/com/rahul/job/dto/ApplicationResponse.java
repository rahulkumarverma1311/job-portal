package com.rahul.job.dto;

import com.rahul.job.domain.ApplicationStatus;
import com.rahul.job.response.UserResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponse {

    private Long id;

    private UserResponse candidate;

    private Long employerId;

    private JobResponse jobResponse;

    private CompanyResponse companyResponse;

    private ApplicationStatus status;

    private Long resumeId;

    private String coverLetter;

    private BigDecimal expectedSalary;
    private LocalDate availableFrom;

    private Boolean isStarred;

//    todo
//    private List<ApplicationNoteResponse> statusHistory;

    private LocalDateTime withdrawnAt;
    private String withdrawnReason;

    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;


//    private ApplicationScreeningResponse screeningResponse;
}
