package com.rahul.job.service.serviceImpl;

import com.rahul.job.dto.ResumeSkillResponse;
import com.rahul.job.mapper.ResumeSkillMapper;
import com.rahul.job.model.Resume;
import com.rahul.job.model.ResumeSkill;
import com.rahul.job.payload.createResumeSkillRequest;
import com.rahul.job.repository.ResumeSkillRepository;
import com.rahul.job.service.ResumeService;
import com.rahul.job.service.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeSkillServiceImpl implements ResumeSkillService{

    private final ResumeSkillRepository resumeSkillRepository;
    private final ResumeService resumeService;

    @Override
    public ResumeSkillResponse addResumeSkill(Long resumeId, Long candidateId, createResumeSkillRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);
        ResumeSkill skill = ResumeSkill.builder()
                .skillName(req.getSkillName())
                .resume(resume)
                .yearsOfExperience(req.getYearsOfExperience())
                .displayOrder(req.getDisplayOrder() != null?req.getDisplayOrder():0)
                .proficiancyLevel(req.getProficiencyLevel())
                .createAt(LocalDateTime.now())
                .build();
        ResumeSkill saved = resumeSkillRepository.save(skill);
        return ResumeSkillMapper.toResponse(skill);
    }

    @Override
    public List<ResumeSkillResponse> getSkills(Long resumeId) {
        return resumeSkillRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId).stream().map(ResumeSkillMapper::toResponse).toList();
    }

    @Override
    public ResumeSkillResponse updateResumeSkill(Long skillId, Long resumeId, Long candidateId, createResumeSkillRequest req) throws Exception {
        ResumeSkill resumeSkill = getResumeSkillEntityById(resumeId);
        assertOwner(resumeSkill.getResume(),candidateId);
        resumeSkill.setSkillName(req.getSkillName());
        resumeSkill.setProficiancyLevel(req.getProficiencyLevel());
        resumeSkill.setYearsOfExperience(req.getYearsOfExperience());
        if(req.getDisplayOrder() != null) resumeSkill.setDisplayOrder(req.getDisplayOrder());
        return ResumeSkillMapper.toResponse(resumeSkillRepository.save(resumeSkill));
    }

    @Override
    public ResumeSkill getResumeSkillEntityById(Long skillId) throws Exception {
        return resumeSkillRepository.findById(skillId).orElseThrow(() -> new Exception("resume skill not found"));

    }

    @Override
    public void deleteResumeSkill(Long candidateId, Long resumeId, Long skillId) throws Exception {
        ResumeSkill resumeSkill = resumeSkillRepository.findById(skillId).orElseThrow(() -> new Exception("resume skill not found"));
        resumeSkillRepository.delete(resumeSkill);
    }


    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("resume not found with id");
        }
    }
}
