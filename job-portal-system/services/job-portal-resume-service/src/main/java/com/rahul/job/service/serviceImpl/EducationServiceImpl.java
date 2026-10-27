package com.rahul.job.service.serviceImpl;

import com.rahul.job.dto.EducationResponse;
import com.rahul.job.mapper.ResumeMapper;
import com.rahul.job.model.Education;
import com.rahul.job.model.Resume;
import com.rahul.job.payload.EducationCreateRequest;
import com.rahul.job.repository.EducationRepository;
import com.rahul.job.service.EducationService;
import com.rahul.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ResumeService resumeService;


    @Override
    public EducationResponse addEducation(Long resumeId, Long candidateId, EducationCreateRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);
        Education education = Education.builder()
                .resume(resume)
                .institutionsName(req.getInstitutionName())
                .degree(req.getDegree())
                .fieldOfStudy(req.getFieldOfStudy())
                .grade(req.getGrade())
                .description(req.getDescription())
                .isCurrentlyStudying(Boolean.TRUE.equals(req.getIsCurrentlyStudying()))
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .displayOrder(req.getDisplayOrder()!= null? req.getDisplayOrder() : 0)
                .build();
        Education saved = educationRepository.save(education);
        return ResumeMapper.toEducationResponse(saved);
    }

    @Override
    public List<EducationResponse> getEducations(Long resumeId) {
        return educationRepository.findByResume_IdOrderByDisplayOrderAse(resumeId).stream().map(ResumeMapper::toEducationResponse).toList();
    }

    @Override
    public EducationResponse updateEducation(Long educationId,Long resumeId, Long candidateId, EducationCreateRequest req) throws Exception {
        Education edu = educationRepository.findById(educationId).orElseThrow(() -> new Exception("education not found"));
        assertOwner(edu.getResume(),candidateId);
        edu.setInstitutionsName(req.getInstitutionName());
        edu.setDegree(req.getDegree());
        edu.setFieldOfStudy(req.getFieldOfStudy());
        edu.setGrade(req.getGrade());
        edu.setStartDate(req.getStartDate());
        edu.setEndDate(req.getEndDate());
        edu.setIsCurrentlyStudying(
                Boolean.TRUE.equals(req.getIsCurrentlyStudying())
        );
        edu.setDescription(req.getDescription());

        if (req.getDisplayOrder() != null) {
            edu.setDisplayOrder(req.getDisplayOrder());
        }


        return ResumeMapper.toEducationResponse(educationRepository.save(edu));
    }

    @Override
    public void deleteEducation(Long resumeId, Long candidateId, Long educationId) throws Exception {
        Education edu = educationRepository.findById(educationId).orElseThrow(() -> new Exception("education not found"));
        assertOwner(edu.getResume(),candidateId);
        educationRepository.delete(edu);

    }


    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("resume not found with id");
        }
    }
}
