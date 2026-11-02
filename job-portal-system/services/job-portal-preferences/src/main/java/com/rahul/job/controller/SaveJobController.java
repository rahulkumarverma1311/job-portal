package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.SaveJobResponse;
import com.rahul.job.payload.SaveJobRequest;
import com.rahul.job.service.SaveJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences/saved-jobs")
@RequiredArgsConstructor
public class SaveJobController {


    private final SaveJobService saveJobService;


    @PostMapping
    public ResponseEntity<SaveJobResponse> saveJob(
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody SaveJobRequest request
    ) throws Exception {

        SaveJobResponse response =
                saveJobService.saveJob(candidateId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SaveJobResponse>> getSavedJobs(
            @RequestHeader("X-User-Id") Long candidateId
    ) {

        List<SaveJobResponse> response =
                saveJobService.getSavedJobs(candidateId);

        return ResponseEntity.ok(response);
    }

    // Check if Job is Saved
    @GetMapping("/check")
    public ResponseEntity<Boolean> isSaved(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestParam Long jobId
    ) {

        Boolean saved =
                saveJobService.isSaved(candidateId, jobId);

        return ResponseEntity.ok(saved);
    }

    // Unsave Job
    @DeleteMapping("/{savedJobId}")
    public ResponseEntity<ApiResponse> unSaveJob(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long savedJobId
    ) throws Exception {

        saveJobService.unSaveJob(candidateId, savedJobId);

        return ResponseEntity.ok(new ApiResponse("Job removed from saved job list",true));
    }

}
