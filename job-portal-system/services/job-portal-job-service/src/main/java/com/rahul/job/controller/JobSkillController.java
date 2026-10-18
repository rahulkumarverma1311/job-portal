package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.JobSkillResponse;
import com.rahul.job.model.JobSkill;
import com.rahul.job.payload.JobSkillRequest;
import com.rahul.job.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-skill")
public class JobSkillController {

    private final JobSkillService jobSkillService;

    @PostMapping
    public ResponseEntity<JobSkillResponse> createJobSkill(
            @Valid @RequestBody JobSkillRequest request) throws Exception {

        JobSkillResponse response =
                jobSkillService.createJobSkill(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<JobSkillResponse>> getAllJobSkills() {

        return ResponseEntity.ok(jobSkillService.getAllJobSkills());
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getJobSkillById(
            @PathVariable Long id) throws Exception {

        return ResponseEntity.ok(jobSkillService.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateJobSkill(
            @PathVariable Long id,
            @Valid @RequestBody JobSkillRequest request) throws Exception {

        return ResponseEntity.ok(
                jobSkillService.updateJobSkill(id, request));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJobSkill(
            @PathVariable Long id) throws Exception {

        jobSkillService.deleteJobSkill(id);

        return ResponseEntity.ok(new ApiResponse("JobSkill is deleted successfully",true));
    }


//    @PostMapping("/by-ids")
//    public ResponseEntity<Set<JobSkill>> getSkillsByIds(
//            @RequestBody Set<Long> ids) {
//
//        return ResponseEntity.ok(jobSkillService.getSkillsByIds(ids));
//    }
}
