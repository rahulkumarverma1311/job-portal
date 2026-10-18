package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.JobTagResponse;
import com.rahul.job.payload.JobTagRequest;
import com.rahul.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-tags")
public class JobTagController {
    private final JobTagService jobTagService;

    @PostMapping
    public ResponseEntity<JobTagResponse> createJobTag(
            @RequestBody JobTagRequest request) throws Exception {

        JobTagResponse response = jobTagService.createTag(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<JobTagResponse>> getAllJobTags() {

        List<JobTagResponse> tags = jobTagService.getAllTags();

        return ResponseEntity.ok(tags);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getJobTagById(
            @PathVariable Long id) throws Exception {

        JobTagResponse response = jobTagService.getById(id);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateJobTag(
            @PathVariable Long id,
            @RequestBody JobTagRequest request) throws Exception {

        JobTagResponse response = jobTagService.updateTag(id, request);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTag(
            @PathVariable Long id) throws Exception {

        jobTagService.deleteTag(id);

        return ResponseEntity.ok(new ApiResponse("Job Tag Deleted successfully",true));
    }


}
