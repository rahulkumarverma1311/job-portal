package com.rahul.job.service.impl;

import com.rahul.job.dto.SaveJobResponse;
import com.rahul.job.mapper.PreferencesMapper;
import com.rahul.job.model.SavedJob;
import com.rahul.job.payload.SaveJobRequest;
import com.rahul.job.repository.SavedJobsRepository;
import com.rahul.job.service.SaveJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SaveJobServiceImpl implements SaveJobService {

    private final SavedJobsRepository savedJobsRepository;


    @Override
    public SaveJobResponse saveJob(Long candidateId, SaveJobRequest req) throws Exception {
        if(isSaved(candidateId, req.getJobId())){
            throw new Exception("Job Already Saved");
        }
        SavedJob savedJob = SavedJob.builder()
                .candidateId(candidateId)
                .jobId(req.getJobId())
//                .savedAt(LocalDateTime.now())
                .build();
        SavedJob save = savedJobsRepository.save(savedJob);
        return PreferencesMapper.toSaveJobResponse(save);
    }

    @Override
    public void unSaveJob(Long candidateId, Long savedJobId) throws Exception {
        SavedJob savedJob = savedJobsRepository.findById(savedJobId).orElseThrow(() -> new Exception("saved job not found"));
        if(!savedJob.getCandidateId().equals(candidateId)){
            throw new Exception("Job is not saved");
        }
        savedJobsRepository.delete(savedJob);
    }

    @Override
    public List<SaveJobResponse> getSavedJobs(Long candidateId) {
        return savedJobsRepository.findByCandidateId(candidateId).stream().map(PreferencesMapper::toSaveJobResponse).toList();
    }

    @Override
    public Boolean isSaved(Long candidateId, Long jobId) {
        return savedJobsRepository.existsByCandidateIdAndJobId(candidateId,jobId);
    }
}
