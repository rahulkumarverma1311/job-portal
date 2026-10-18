package com.rahul.job.service.serviceImpl;

import com.rahul.job.dto.WorkExperienceResponse;
import com.rahul.job.mapper.WorkExperienceMapper;
import com.rahul.job.model.Resume;
import com.rahul.job.model.WorkExperience;
import com.rahul.job.payload.AddWorkExperienceRequest;
import com.rahul.job.repository.WorkExperienceRepository;
import com.rahul.job.service.ResumeService;
import com.rahul.job.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final ResumeService resumeService;

    @Override
    public WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);

        WorkExperience workExperience = WorkExperience.builder()
                .resume(resume)
                .companyName(req.getCompanyName())
                .companyLogoUrl(req.getCompanyLogoUrl())
                .jobTitle(req.getJobTitle())
                .employmentType(req.getEmploymentType())
                .location(req.getLocation())
                .technologies(req.getTechnologies() !=null? req.getTechnologies():List.of())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .isCurrentJob(req.getIsCurrentJob())
                .description(req.getDescription())
                .displayOrder(req.getDisplayOrder() != null? req.getDisplayOrder():0)
                .build();
        WorkExperience saved = workExperienceRepository.save(workExperience);

        return WorkExperienceMapper.toResponse(saved);
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperiences(Long resumeId) {
        return workExperienceRepository.findByResume_idOrderByDisplayOrderAsc(resumeId).stream().map(WorkExperienceMapper::toResponse).toList();
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long candidateId,Long workExperienceId, AddWorkExperienceRequest req) throws Exception {
        WorkExperience exp = getWorkExperienceEntity(workExperienceId);
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);
        exp.setCompanyName(req.getCompanyName());
        exp.setCompanyLogoUrl(req.getCompanyLogoUrl());
        exp.setJobTitle(req.getJobTitle());
        exp.setDescription(req.getDescription());
        exp.setEndDate(req.getEndDate());
        exp.setStartDate(req.getStartDate());
        exp.setIsCurrentJob(Boolean.TRUE.equals(req.getIsCurrentJob()));
        exp.setLocation(req.getLocation());
        exp.setEmploymentType(req.getEmploymentType());
        if(req.getTechnologies() != null){
            exp.setTechnologies(req.getTechnologies());
        }
        if(req.getDisplayOrder()!= null){
            exp.setDisplayOrder(req.getDisplayOrder());
        }
        WorkExperience saved = workExperienceRepository.save(exp);
        return WorkExperienceMapper.toResponse(saved);
    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long workExperiences, Long candidateId) throws Exception {
        WorkExperience exp = getWorkExperienceEntity(workExperiences);
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);
        workExperienceRepository.delete(exp);
    }

    @Override
    public WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception {
        return workExperienceRepository.findById(workExperienceId).orElseThrow(() -> new Exception("work experience is not found"));
    }


    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("resume not found with id");
        }
    }
}
