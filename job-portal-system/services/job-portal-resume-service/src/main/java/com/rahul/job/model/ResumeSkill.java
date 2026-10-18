package com.rahul.job.model;

import com.rahul.job.domain.ProficiancyLevel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "resume_skills")
public class ResumeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;
    @Column(nullable = false)
    private String skillName;
    @Enumerated(EnumType.STRING)
    private ProficiancyLevel proficiancyLevel = ProficiancyLevel.BEGINNER;
    private Integer yearsOfExperience;
    @Column(nullable = false)
    private Integer displayOrder=0;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;




}
