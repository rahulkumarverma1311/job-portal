package com.rahul.job.repository;

import com.rahul.job.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill,Long> {

    Boolean existsByName(String name);
    Boolean existsBySlug(String slug);
    List<JobSkill>findByActiveTrue();

}
