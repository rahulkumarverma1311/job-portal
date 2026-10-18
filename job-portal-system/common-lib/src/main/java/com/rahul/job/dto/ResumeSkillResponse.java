package com.rahul.job.dto;

import com.rahul.job.domain.ProficiancyLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeSkillResponse {

    private Long id;
    private String skillName;
    private ProficiancyLevel proficiancyLevel;
    private Integer yearsOfExperience;
    private Integer displayOrder;

}
