package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.PersonalInformationResponse;
import com.rahul.job.dto.ResumeResponse;
import com.rahul.job.payload.CreateResumeRequest;
import com.rahul.job.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid CreateResumeRequest request) {

        ResumeResponse response =
                resumeService.createResume(candidateId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId)
            throws Exception {

        return ResponseEntity.ok(
                resumeService.getResumeById(resumeId, candidateId)
        );
    }

    @GetMapping("/my")
    public ResponseEntity<List<ResumeResponse>> getMyResumes(
            @RequestHeader("X-User-Id") Long candidateId) {

        return ResponseEntity.ok(
                resumeService.getMyResumes(candidateId)
        );
    }

    @PatchMapping("/{resumeId}/personal-info")
    public ResponseEntity<ResumeResponse> updatePersonalInfo(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid PersonalInformationResponse request)
            throws Exception {

        return ResponseEntity.ok(
                resumeService.updatePersonalInfo(
                        resumeId, candidateId, request
                )
        );
    }

    @PatchMapping("/{resumeId}/summary")
    public ResponseEntity<ResumeResponse> updateSummary(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestParam String summary) throws Exception {

        return ResponseEntity.ok(
                resumeService.updateSummary(
                        resumeId, candidateId, summary
                )
        );
    }

    @PatchMapping("/{resumeId}/set-default")
    public ResponseEntity<ResumeResponse> setDefaultResume(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId)
            throws Exception {

        return ResponseEntity.ok(
                resumeService.setDefaultResume(resumeId, candidateId)
        );
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<ApiResponse> deleteResume(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId)
            throws Exception {

        resumeService.deleteResume(resumeId, candidateId);

        return ResponseEntity.ok(new ApiResponse("resume deleted successfully",true));
    }


}
