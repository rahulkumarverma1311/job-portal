package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.CertificationResponse;
import com.rahul.job.payload.CertificationCreateRequest;
import com.rahul.job.service.CertificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    // Add certification
    @PostMapping
    public ResponseEntity<CertificationResponse> addCertification(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody CertificationCreateRequest req
    ) throws Exception {

        CertificationResponse response =
                certificationService.addCertification(
                        resumeId, candidateId, req
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Get all certifications
    @GetMapping
    public ResponseEntity<List<CertificationResponse>> getCertifications(
            @PathVariable Long resumeId
    ) {

        return ResponseEntity.ok(
                certificationService.getCertifications(resumeId)
        );
    }

    // Update certification
    @PutMapping("/{certificationId}")
    public ResponseEntity<CertificationResponse> updateCertification(
            @PathVariable Long resumeId,
            @PathVariable Long certificationId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody CertificationCreateRequest req
    ) throws Exception {

        CertificationResponse response =
                certificationService.updateCertification(
                        certificationId,
                        resumeId,
                        candidateId,
                        req
                );

        return ResponseEntity.ok(response);
    }

    // Delete certification
    @DeleteMapping("/{certificationId}")
    public ResponseEntity<ApiResponse> deleteCertification(
            @PathVariable Long resumeId,
            @PathVariable Long certificationId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        certificationService.deleteCertification(
                resumeId,
                candidateId,
                certificationId
        );

        return ResponseEntity.ok(
                new ApiResponse(
                        "Certification deleted successfully",
                        true
                )
        );
    }
}