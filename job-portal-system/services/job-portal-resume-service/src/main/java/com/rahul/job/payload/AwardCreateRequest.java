package com.rahul.job.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardCreateRequest {

    @NotBlank(message = "Award title is required")
    private String title;

    private String issuer;

    private LocalDate dateReceived;

    private String description;

    private Integer displayOrder;
}