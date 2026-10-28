package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.AwardResponse;
import com.rahul.job.payload.AwardCreateRequest;
import com.rahul.job.service.AwardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/awards")
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;

    // Add award
    @PostMapping
    public ResponseEntity<AwardResponse> addAward(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody AwardCreateRequest req
    ) throws Exception {

        AwardResponse response =
                awardService.addAward(resumeId, candidateId, req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Get all awards
    @GetMapping
    public ResponseEntity<List<AwardResponse>> getAwards(
            @PathVariable Long resumeId
    ) {

        return ResponseEntity.ok(
                awardService.getAwards(resumeId)
        );
    }

    // Update award
    @PutMapping("/{awardId}")
    public ResponseEntity<AwardResponse> updateAward(
            @PathVariable Long resumeId,
            @PathVariable Long awardId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody AwardCreateRequest req
    ) throws Exception {

        AwardResponse response = awardService.updateAward(
                awardId,
                resumeId,
                candidateId,
                req
        );

        return ResponseEntity.ok(response);
    }

    // Delete award
    @DeleteMapping("/{awardId}")
    public ResponseEntity<ApiResponse> deleteAward(
            @PathVariable Long resumeId,
            @PathVariable Long awardId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        awardService.deleteAward(
                resumeId,
                candidateId,
                awardId
        );

        return ResponseEntity.ok(
                new ApiResponse("Award deleted successfully", true)
        );
    }
}