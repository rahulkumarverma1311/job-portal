package com.rahul.job.service.serviceImpl;

import com.rahul.job.dto.CertificationResponse;
import com.rahul.job.mapper.ResumeMapper;
import com.rahul.job.model.Certification;
import com.rahul.job.model.Resume;
import com.rahul.job.payload.CertificationCreateRequest;
import com.rahul.job.repository.CertificationRepository;
import com.rahul.job.service.CertificationService;
import com.rahul.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;
    private final ResumeService resumeService;

    @Override
    public CertificationResponse addCertification(
            Long resumeId,
            Long candidateId,
            CertificationCreateRequest req
    ) throws Exception {

        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume, candidateId);

        Certification certification = Certification.builder()
                .resume(resume)
                .name(req.getName())
                .issuingOrganization(req.getIssuingOrganization())
                .issueDate(req.getIssueDate())
                .expirationDate(req.getExpirationDate())
//                .credentialId(req.getCredentialId())
//                .credentialUrl(req.getCredentialUrl())
                .description(req.getDescription())
                .doesNotExpire(
                        Boolean.TRUE.equals(req.getDoesNotExpire())
                )
                .displayOrder(
                        req.getDisplayOrder() != null
                                ? req.getDisplayOrder()
                                : 0
                )
                .build();

        Certification saved =
                certificationRepository.save(certification);

        return ResumeMapper.toCertificationResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CertificationResponse> getCertifications(Long resumeId) {

        return certificationRepository
                .findByResume_IdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toCertificationResponse)
                .toList();
    }

    @Override
    public CertificationResponse updateCertification(
            Long certificationId,
            Long resumeId,
            Long candidateId,
            CertificationCreateRequest req
    ) throws Exception {

        Certification certification =
                getCertificationEntityById(certificationId);

        validateResume(certification, resumeId, candidateId);

        certification.setName(req.getName());
        certification.setIssuingOrganization(
                req.getIssuingOrganization()
        );
        certification.setIssueDate(req.getIssueDate());
        certification.setExpirationDate(req.getExpirationDate());
//        certification.setCredentialId(req.getCredentialId());
//        certification.setCredentialUrl(req.getCredentialUrl());
        certification.setDescription(req.getDescription());
        certification.setDoesNotExpire(
                Boolean.TRUE.equals(req.getDoesNotExpire())
        );

        if (req.getDisplayOrder() != null) {
            certification.setDisplayOrder(req.getDisplayOrder());
        }

        Certification saved =
                certificationRepository.save(certification);

        return ResumeMapper.toCertificationResponse(saved);
    }

    @Override
    public void deleteCertification(
            Long resumeId,
            Long candidateId,
            Long certificationId
    ) throws Exception {

        Certification certification =
                getCertificationEntityById(certificationId);

        validateResume(certification, resumeId, candidateId);

        certificationRepository.delete(certification);
    }

    private Certification getCertificationEntityById(
            Long certificationId
    ) throws Exception {

        return certificationRepository.findById(certificationId)
                .orElseThrow(
                        () -> new Exception("Certification not found")
                );
    }

    private void validateResume(
            Certification certification,
            Long resumeId,
            Long candidateId
    ) throws Exception {

        if (!certification.getResume().getId().equals(resumeId)) {
            throw new Exception(
                    "Certification not found for this resume"
            );
        }

        assertOwner(certification.getResume(), candidateId);
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