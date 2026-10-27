package com.rahul.job.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;
    @Column(nullable = false)
    private String title;

    private String descriptions;
    @ElementCollection
    private List<String> technologies = new ArrayList<>();

    private String projectUrl;

    private String sourceCodeUrl;

    private LocalDate startDate;
    private LocalDate endDate;
    @Column(nullable = false)
    @Builder.Default
    private Boolean isOnGoing = false;


    @Column(nullable = false)
    private Integer displayOrder = 0;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;


    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
