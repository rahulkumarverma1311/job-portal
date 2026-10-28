package com.rahul.job.service;

import com.rahul.job.dto.LanguageResponse;
import com.rahul.job.payload.AddLanguageRequest;

import java.util.List;

public interface LanguageService {
    LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception;
    List<LanguageResponse>getAllLanguage(Long resumeId);
    LanguageResponse updateLanguage(Long languageId,Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception;
    void deleteLanguage(Long languageId,Long resumeId, Long candidateId) throws Exception;
}
