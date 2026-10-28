package com.rahul.job.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardResponse {

    private Long id;

    private String title;

    private String issuer;

    private LocalDate dateReceived;

    private String description;

    private Integer displayOrder;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;
}