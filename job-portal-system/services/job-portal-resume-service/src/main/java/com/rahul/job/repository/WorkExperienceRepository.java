package com.rahul.job.repository;

import com.rahul.job.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience,Long> {

    List<WorkExperience> findByResume_idOrderByDisplayOrderAsc(Long resumeId);
}
