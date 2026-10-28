package com.rahul.job.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationCreateRequest {

    @NotBlank(message = "Certification name is required")
    private String name;

    @NotBlank(message = "Issuing organization is required")
    private String issuingOrganization;

    private LocalDate issueDate;

    private LocalDate expirationDate;

//    private String credentialId;
//
//    private String credentialUrl;

    private String description;

    private Boolean doesNotExpire;

    private Integer displayOrder;
}