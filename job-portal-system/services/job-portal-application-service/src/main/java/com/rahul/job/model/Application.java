package com.rahul.job.model;

import com.rahul.job.domain.AiShortListStatus;
import com.rahul.job.domain.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private Long candidateId;

    @Column(nullable = false)
    private Long jobId;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Long employerId;

    @Column(nullable = false)
    private Long resumeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    private String coverLetter;

    private BigDecimal expectedSalary;

    private LocalDate availableFrom;

    private LocalDateTime withdrawnAt;

    private String withdrawnReason;

    private Boolean isStarred = false;

    private Integer aiScore;

    private AiShortListStatus aiShortListStatus ;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;



}
