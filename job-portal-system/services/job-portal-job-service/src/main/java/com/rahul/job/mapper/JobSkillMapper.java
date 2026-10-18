package com.rahul.job.mapper;

import com.rahul.job.dto.JobSkillResponse;
import com.rahul.job.model.JobSkill;

public class JobSkillMapper {

    public static JobSkillResponse convertToResponse(JobSkill skill){
        return JobSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .slug(skill.getSlug())
                .category(skill.getCategory())
                .active(skill.getActive())
                .build();
    }
}
