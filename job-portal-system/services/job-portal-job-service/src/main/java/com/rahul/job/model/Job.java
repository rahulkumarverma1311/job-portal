package com.rahul.job.model;

import com.rahul.job.domain.ExperienceLevel;
import com.rahul.job.domain.JobStatus;
import com.rahul.job.domain.JobType;
import com.rahul.job.domain.WorkMode;
import com.rahul.job.model.embedable.JobLocation;
import com.rahul.job.model.embedable.SalaryRange;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String requirements;

    private String responsibilities;

    private String benefits;

    @Column(nullable = false)
    private Long companyId;
    @Column(nullable = false)
    private Long employeeId;

    @ManyToOne
    private JobCategory jobCategory;
    @ManyToMany
    private Set<JobSkill> skills;
    @ManyToMany
    private Set<JobTag> jobTags;
    @Embedded
    private JobLocation location;
    @Embedded
    private SalaryRange salaryRange;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus = JobStatus.DRAFT;
    private Integer openings = 1;
    private LocalDate applicationDeadLine;
    private LocalDate expiresAt;
    private Boolean active = true;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;
    private LocalDateTime publishedAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    private LocalDateTime closeAt;


}


