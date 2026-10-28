package com.rahul.job.service;

import com.rahul.job.dto.AwardResponse;
import com.rahul.job.payload.AwardCreateRequest;

import java.util.List;

public interface AwardService {

    AwardResponse addAward(
            Long resumeId,
            Long candidateId,
            AwardCreateRequest req
    ) throws Exception;

    List<AwardResponse> getAwards(Long resumeId);

    AwardResponse updateAward(
            Long awardId,
            Long resumeId,
            Long candidateId,
            AwardCreateRequest req
    ) throws Exception;

    void deleteAward(
            Long resumeId,
            Long candidateId,
            Long awardId
    ) throws Exception;
}