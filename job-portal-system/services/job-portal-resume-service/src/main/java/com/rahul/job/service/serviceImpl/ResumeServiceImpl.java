package com.rahul.job.service.serviceImpl;


import com.rahul.job.dto.PersonalInformationResponse;
import com.rahul.job.dto.ResumeResponse;
import com.rahul.job.mapper.ResumeMapper;
import com.rahul.job.model.PersonalInfo;
import com.rahul.job.model.Resume;
import com.rahul.job.payload.CreateResumeRequest;
import com.rahul.job.repository.ResumeRepository;
import com.rahul.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public ResumeResponse createResume(Long candidateId, CreateResumeRequest req) {
        if (Boolean.TRUE.equals(req.getIsDefault())) {
            resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId).ifPresent(e -> {
                e.setIsDefault(false);
                e.setIsActive(false);
                resumeRepository.save(e);
            });
        }
        Resume resume = Resume.builder()
                .candidateId(candidateId)
                .title(req.getTitle())
                .createAt(LocalDateTime.now())
                .template(req.getTemplate())
                .isDefault(Boolean.TRUE.equals(req.getIsDefault()))
                .visibility(req.getVisibility())
                .isActive(true)
                .build();
        Resume save = resumeRepository.save(resume);
        return buildFullResumeResponse(save);
    }

    @Override
    public ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        assertOwner(resume, candidateId);

        return buildFullResumeResponse(resume);
    }


    @Override
    public List<ResumeResponse> getMyResumes(Long candidateId) {
        return resumeRepository.findByCandidateIdAndIsActiveTrue(candidateId).stream().map(this::buildFullResumeResponse).toList();
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInformationResponse req) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        assertOwner(resume, candidateId);
        PersonalInfo info = resume.getPersonalInfo();
        if (info == null) {
           info =  new PersonalInfo();
        }
        if (req.getFirstName() != null)
            info.setFirstName(req.getFirstName());

        if (req.getLastName() != null)
            info.setLastName(req.getLastName());

        if (req.getHeadline() != null)
            info.setHeadline(req.getHeadline());

        if (req.getEmail() != null)
            info.setEmail(req.getEmail());

        if (req.getPhoneNumber() != null)
            info.setPhoneNumber(req.getPhoneNumber());

        if (req.getCity() != null)
            info.setCity(req.getCity());

        if (req.getCountry() != null)
            info.setCountry(req.getCountry());

        if (req.getLinkedinUrl() != null)
            info.setLinkedinUrl(req.getLinkedinUrl());

        if (req.getGithubUrl() != null)
            info.setGithubUrl(req.getGithubUrl());

        if (req.getPortfolioUrl() != null)
            info.setPortfolioUrl(req.getPortfolioUrl());

        if (req.getWebsiteUrl() != null)
            info.setWebsiteUrl(req.getWebsiteUrl());

        resume.setPersonalInfo(info);
        Resume save = resumeRepository.save(resume);
        return buildFullResumeResponse(save);
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        assertOwner(resume, candidateId);
        resume.setSummary(summary);
        Resume save = resumeRepository.save(resume);
        return buildFullResumeResponse(save);
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        assertOwner(resume, candidateId);
        resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId).ifPresent(e->{
            e.setIsDefault(false);
            resumeRepository.save(e);
        });
        resume.setIsDefault(true);
        Resume save = resumeRepository.save(resume);

        return buildFullResumeResponse(save);
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntityById(resumeId);
        assertOwner(resume, candidateId);
        resume.setIsActive(false);
        resume.setIsDefault(false);
        resumeRepository.save(resume);

    }

    @Override
    public Resume getResumeEntityById(Long id) throws Exception {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new Exception("resume not found with id:- " + id));
        return resume;
    }


    private ResumeResponse buildFullResumeResponse(Resume resume) {
        return ResumeMapper.toResponse(resume);
    }


    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("resume not found with id");
        }
    }
}
