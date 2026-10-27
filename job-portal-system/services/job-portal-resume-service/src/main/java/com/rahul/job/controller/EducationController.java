package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.EducationResponse;
import com.rahul.job.payload.EducationCreateRequest;
import com.rahul.job.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/educations")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;



    // Add education
    @PostMapping
    public ResponseEntity<EducationResponse> addEducation(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody EducationCreateRequest req
    ) throws Exception {

        EducationResponse response =
                educationService.addEducation(resumeId, candidateId, req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all education records for a resume
    @GetMapping
    public ResponseEntity<List<EducationResponse>> getEducations(
            @PathVariable Long resumeId
    ) {
        return ResponseEntity.ok(
                educationService.getEducations(resumeId)
        );
    }

    // Update education
    @PutMapping("/{educationId}")
    public ResponseEntity<EducationResponse> updateEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody EducationCreateRequest req
    ) throws Exception {

        EducationResponse response =
                educationService.updateEducation(
                        educationId, resumeId, candidateId, req
                );

        return ResponseEntity.ok(response);
    }

    // Delete education
    @DeleteMapping("/{educationId}")
    public ResponseEntity<ApiResponse> deleteEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        educationService.deleteEducation(
                resumeId, candidateId, educationId
        );

        return ResponseEntity.ok(new ApiResponse("Education deleted successfully",true));
    }
}
