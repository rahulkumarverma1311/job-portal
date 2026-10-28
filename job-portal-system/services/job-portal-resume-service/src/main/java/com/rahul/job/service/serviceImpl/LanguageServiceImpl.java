package com.rahul.job.service.serviceImpl;

import com.rahul.job.dto.LanguageResponse;
import com.rahul.job.mapper.ResumeMapper;
import com.rahul.job.model.Language;
import com.rahul.job.model.Resume;
import com.rahul.job.payload.AddLanguageRequest;
import com.rahul.job.repository.LanguageRepository;
import com.rahul.job.service.LanguageService;
import com.rahul.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ResumeService resumeService;
    @Override
    public LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);
        Language language = Language.builder()
                .resume(resume)
                .languageName(req.getLanguageName())
                .proficiency(req.getProficiency())
                .displayOrder(req.getDisplayOrder() !=null? req.getDisplayOrder():0)
                .build();
        Language save = languageRepository.save(language);
        return ResumeMapper.toLanguageResponse(save);
    }

    @Override
    public List<LanguageResponse> getAllLanguage(Long resumeId) {
        return languageRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId).stream().map(ResumeMapper::toLanguageResponse).toList();
    }

    @Override
    public LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception {
        Language language = languageRepository.findById(languageId).orElseThrow(() -> new Exception("Language not found"));
        assertOwner(language.getResume(),candidateId);
        language.setLanguageName(req.getLanguageName());
        language.setProficiency(req.getProficiency());
        language.setDisplayOrder(req.getDisplayOrder());
        return ResumeMapper.toLanguageResponse(languageRepository.save(language));
    }

    @Override
    public void deleteLanguage(Long languageId, Long resumeId, Long candidateId) throws Exception {
        Language language = languageRepository.findById(languageId).orElseThrow(() -> new Exception("Language not found"));
        assertOwner(language.getResume(),candidateId);
        languageRepository.delete(language);

    }


    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("resume not found with id");
        }
    }
}
