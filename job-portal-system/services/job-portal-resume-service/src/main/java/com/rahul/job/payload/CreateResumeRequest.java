package com.rahul.job.payload;

import com.rahul.job.domain.ResumeTemplate;
import com.rahul.job.domain.ResumeVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResumeRequest {
    @NotBlank(message = "Resume Title is required")
    private String title;

    private ResumeTemplate template;
    private ResumeVisibility visibility;
    private Boolean isDefault;
}
