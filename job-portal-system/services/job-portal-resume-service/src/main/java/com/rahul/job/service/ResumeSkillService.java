package com.rahul.job.service;

import com.rahul.job.dto.ResumeSkillResponse;
import com.rahul.job.model.ResumeSkill;
import com.rahul.job.payload.createResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {

    ResumeSkillResponse addResumeSkill(Long resumeId, Long candidateId, createResumeSkillRequest req) throws Exception;
    List<ResumeSkillResponse> getSkills(Long resumeId);
    ResumeSkillResponse updateResumeSkill(Long skillId,Long resumeId,Long candidateId,createResumeSkillRequest req) throws Exception;
    ResumeSkill getResumeSkillEntityById(Long skillId) throws Exception;
    void deleteResumeSkill(Long candidateId,Long resumeId,Long skillId) throws Exception;
}
