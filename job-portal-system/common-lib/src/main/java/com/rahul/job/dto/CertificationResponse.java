package com.rahul.job.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationResponse {

    private Long id;

    private String name;

    private String issuingOrganization;

    private LocalDate issueDate;

    private LocalDate expirationDate;

//    private String credentialId;
//
//    private String credentialUrl;

    private String description;

    private Boolean doesNotExpire;

    private Integer displayOrder;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;
}