package com.rahul.job.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false, length = 200)
    private String institutionsName;

    @Column(nullable = false, length = 200)
    private String degree;

    @Column(nullable = false, length = 200)
    private String fieldOfStudy;

    @Column(length = 200)
    private String grade;

    private String description;

    @Column(nullable = false)
    private Integer displayOrder=0;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;
    @Column(nullable = false)
    @Builder.Default
    private Boolean isCurrentlyStudying= false;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;


    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
