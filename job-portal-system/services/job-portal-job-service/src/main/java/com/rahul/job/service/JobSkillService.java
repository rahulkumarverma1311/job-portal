package com.rahul.job.service;

import com.rahul.job.dto.JobSkillResponse;
import com.rahul.job.model.JobSkill;
import com.rahul.job.payload.JobSkillRequest;

import java.util.List;
import java.util.Set;

public interface JobSkillService {

    JobSkillResponse createJobSkill(JobSkillRequest request) throws Exception;

    List<JobSkillResponse> getAllJobSkills();

    JobSkillResponse getById(Long id) throws Exception;

    JobSkillResponse updateJobSkill(Long id ,JobSkillRequest req) throws Exception;

    void deleteJobSkill(Long id) throws Exception;

    Set<JobSkill> getSkillsByIds(Set<Long> ids);

}
