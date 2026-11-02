package com.rahul.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveJobResponse {

    private Long id;
    private Long candidateID;
    private Long jobId;
    private LocalDateTime savedAt;

}
