package com.rahul.job.repository;

import com.rahul.job.model.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedJobsRepository extends JpaRepository<SavedJob,Long> {

    List<SavedJob>findByCandidateId(Long id);

    boolean existsByCandidateIdAndJobId(Long candidateId,Long jobId);
}
