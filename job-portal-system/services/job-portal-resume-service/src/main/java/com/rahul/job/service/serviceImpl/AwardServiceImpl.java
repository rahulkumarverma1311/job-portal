package com.rahul.job.service.serviceImpl;

import com.rahul.job.dto.AwardResponse;
import com.rahul.job.mapper.ResumeMapper;
import com.rahul.job.model.Award;
import com.rahul.job.model.Resume;
import com.rahul.job.payload.AwardCreateRequest;
import com.rahul.job.repository.AwardRepository;
import com.rahul.job.service.AwardService;
import com.rahul.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final ResumeService resumeService;

    @Override
    public AwardResponse addAward(
            Long resumeId,
            Long candidateId,
            AwardCreateRequest req
    ) throws Exception {

        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume, candidateId);

        Award award = Award.builder()
                .resume(resume)
                .title(req.getTitle())
                .issuer(req.getIssuer())
                .dateReceived(req.getDateReceived())
                .description(req.getDescription())
                .displayOrder(
                        req.getDisplayOrder() != null
                                ? req.getDisplayOrder()
                                : 0
                )
                .build();

        Award saved = awardRepository.save(award);

        return ResumeMapper.toAwardResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AwardResponse> getAwards(Long resumeId) {

        return awardRepository
                .findByResume_IdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toAwardResponse)
                .toList();
    }

    @Override
    public AwardResponse updateAward(
            Long awardId,
            Long resumeId,
            Long candidateId,
            AwardCreateRequest req
    ) throws Exception {

        Award award = getAwardEntityById(awardId);

        validateResume(award, resumeId, candidateId);

        award.setTitle(req.getTitle());
        award.setIssuer(req.getIssuer());
        award.setDateReceived(req.getDateReceived());
        award.setDescription(req.getDescription());

        if (req.getDisplayOrder() != null) {
            award.setDisplayOrder(req.getDisplayOrder());
        }

        Award saved = awardRepository.save(award);

        return ResumeMapper.toAwardResponse(saved);
    }

    @Override
    public void deleteAward(
            Long resumeId,
            Long candidateId,
            Long awardId
    ) throws Exception {

        Award award = getAwardEntityById(awardId);

        validateResume(award, resumeId, candidateId);

        awardRepository.delete(award);
    }

    private Award getAwardEntityById(Long awardId) throws Exception {

        return awardRepository.findById(awardId)
                .orElseThrow(() -> new Exception("Award not found"));
    }

    private void validateResume(
            Award award,
            Long resumeId,
            Long candidateId
    ) throws Exception {

        if (!award.getResume().getId().equals(resumeId)) {
            throw new Exception("Award not found for this resume");
        }

        assertOwner(award.getResume(), candidateId);
    }

    private void assertOwner(
            Resume resume,
            Long candidateId
    ) throws Exception {

        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found with id");
        }
    }
}