package com.rahul.job.entity;

import com.rahul.job.domain.CompanySize;
import com.rahul.job.domain.CompanyStatus;
import com.rahul.job.domain.CompanyType;
import com.rahul.job.domain.IndustryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(unique = true)
    private String slug;
    private String tegLine;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String website;
    @Email
    private String email;
    private String phone;
    private Integer foundedYear;
    @Enumerated(EnumType.STRING)
    private CompanySize companySize;
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;
    @Enumerated(EnumType.STRING)
    private IndustryType industryType;
    private CompanyStatus status;
    @Column(unique = true)
    private String registrationNumber;
    @Column(nullable = false,unique = true)
    private Long ownerId;
    @ElementCollection
    private List<SocialLink> socialLinks= new ArrayList<>();

    private boolean active = true;
    private boolean isVerified = false;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
