package com.rahul.job.repository;

import com.rahul.job.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTagRepository extends JpaRepository<JobTag,Long> {
    Boolean existsByName(String name);
    Boolean existsBySlug(String slug);
}
