package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.WorkExperienceResponse;
import com.rahul.job.payload.AddWorkExperienceRequest;
import com.rahul.job.service.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/work-experiences")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping
    public ResponseEntity<WorkExperienceResponse> addWorkExperience(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid  AddWorkExperienceRequest request
    ) throws Exception {

        WorkExperienceResponse response =
                workExperienceService.addWorkExperience(
                        resumeId, candidateId, request
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperiences(
            @PathVariable Long resumeId
    ) {
        return ResponseEntity.ok(
                workExperienceService.getWorkExperiences(resumeId)
        );
    }


    @PutMapping("/{workExperienceId}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long workExperienceId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody AddWorkExperienceRequest request
    ) throws Exception {

        WorkExperienceResponse response =
                workExperienceService.updateWorkExperience(
                        resumeId, candidateId, workExperienceId, request
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{workExperienceId}")
    public ResponseEntity<ApiResponse> deleteWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long workExperienceId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        workExperienceService.deleteWorkExperience(
                resumeId, workExperienceId, candidateId
        );

        return ResponseEntity.ok(new ApiResponse("work experience deleted successfully",true));
    }



}
