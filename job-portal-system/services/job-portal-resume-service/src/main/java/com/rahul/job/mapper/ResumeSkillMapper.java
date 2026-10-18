package com.rahul.job.mapper;

import com.rahul.job.dto.ResumeSkillResponse;
import com.rahul.job.model.ResumeSkill;


public class ResumeSkillMapper {

    public static ResumeSkillResponse toResponse(ResumeSkill skill){
        if(skill == null) return  null;
        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .skillName(skill.getSkillName())
                .proficiancyLevel(skill.getProficiancyLevel())
                .build();
    }
}
