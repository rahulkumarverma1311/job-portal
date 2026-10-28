package com.rahul.job.service;

import com.rahul.job.dto.CertificationResponse;
import com.rahul.job.payload.CertificationCreateRequest;

import java.util.List;

public interface CertificationService {

    CertificationResponse addCertification(
            Long resumeId,
            Long candidateId,
            CertificationCreateRequest req
    ) throws Exception;

    List<CertificationResponse> getCertifications(Long resumeId);

    CertificationResponse updateCertification(
            Long certificationId,
            Long resumeId,
            Long candidateId,
            CertificationCreateRequest req
    ) throws Exception;

    void deleteCertification(
            Long resumeId,
            Long candidateId,
            Long certificationId
    ) throws Exception;
}