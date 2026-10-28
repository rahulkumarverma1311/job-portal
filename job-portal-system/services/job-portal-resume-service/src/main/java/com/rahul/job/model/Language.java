package com.rahul.job.model;

import com.rahul.job.domain.LanguageProficiency;
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
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;
    @Column(nullable = false)
    private String languageName;
    @Enumerated(EnumType.STRING)
    private LanguageProficiency proficiency;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private Integer displayOrder=0;
}
