package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.ResumeSkillResponse;
import com.rahul.job.payload.createResumeSkillRequest;
import com.rahul.job.service.ResumeSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/skills")
@RequiredArgsConstructor
public class ResumeSkillController {

    private final ResumeSkillService resumeSkillService;


    @PostMapping
    public ResponseEntity<ResumeSkillResponse> addResumeSkill(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid createResumeSkillRequest req
    ) throws Exception {

        ResumeSkillResponse response =
                resumeSkillService.addResumeSkill(resumeId, candidateId, req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<ResumeSkillResponse>> getSkills(
            @PathVariable Long resumeId
    ) {
        return ResponseEntity.ok(resumeSkillService.getSkills(resumeId));
    }

    // Update a skill
    @PutMapping("/{skillId}")
    public ResponseEntity<ResumeSkillResponse> updateResumeSkill(
            @PathVariable Long resumeId,
            @PathVariable Long skillId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid  createResumeSkillRequest req
    ) throws Exception {

        ResumeSkillResponse response =
                resumeSkillService.updateResumeSkill(
                        skillId, resumeId, candidateId, req
                );

        return ResponseEntity.ok(response);
    }

    // Delete a skill
    @DeleteMapping("/{skillId}")
    public ResponseEntity<ApiResponse> deleteResumeSkill(
            @PathVariable Long resumeId,
            @PathVariable Long skillId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        resumeSkillService.deleteResumeSkill(
                candidateId, resumeId, skillId
        );

        return ResponseEntity.ok(new ApiResponse("Resume skill deleted successfully",true));
    }
}
