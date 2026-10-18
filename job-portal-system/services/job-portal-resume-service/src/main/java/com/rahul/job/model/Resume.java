package com.rahul.job.model;

import com.rahul.job.domain.ResumeTemplate;
import com.rahul.job.domain.ResumeVisibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private String title;

    private Boolean isActive =Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeTemplate template = ResumeTemplate.PROFESSIONAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeVisibility visibility = ResumeVisibility.PUBLIC;

    @Column(nullable = false)
    private Boolean isDefault = false;

    private Integer completionScore =0;

    @Embedded
    private PersonalInfo personalInfo;

    private String summary;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;


    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;











}
