package com.rahul.job.repository;

import com.rahul.job.model.ApplicationNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationNoteRepository extends JpaRepository<ApplicationNote,Long> {


    List<ApplicationNote> findByApplicationId(Long id);
}
