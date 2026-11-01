package com.rahul.job.controller;

import com.rahul.job.domain.ApplicationStatus;
import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.ApplicationResponse;
import com.rahul.job.model.Application;
import com.rahul.job.payload.AddApplicationRequest;
import com.rahul.job.payload.CompanyApplicationFilterRequest;
import com.rahul.job.payload.UpdateApplicationStatusReq;
import com.rahul.job.payload.WithdrawApplicationRequest;
import com.rahul.job.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {


    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody AddApplicationRequest req
    ) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.createApplication(candidateId, req));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable Long id
    ) throws Exception {

        return ResponseEntity.ok(
                applicationService.getApplicationById(id)
        );
    }

    // Get applications submitted by logged-in candidate
    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> getMyApplications(
            @RequestHeader("X-User-Id") Long candidateId
    ) {

        return ResponseEntity.ok(
                applicationService.getMyApplication(candidateId)
        );
    }

    // Get applications for company with filters
    @GetMapping("/company")
    public ResponseEntity<List<ApplicationResponse>> getCompanyApplications(
            @RequestHeader("X-User-Id") Long userId,
            @ModelAttribute CompanyApplicationFilterRequest req
    ) {

        return ResponseEntity.ok(
                applicationService.getApplicationForCompany(userId, req)
        );
    }

    // Get all applications for a particular job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsForJob(
            @PathVariable Long jobId
    ) {

        return ResponseEntity.ok(
                applicationService.getApplicationForJob(jobId)
        );
    }

    // Employer updates application status
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid UpdateApplicationStatusReq req
    ) throws Exception {

        return ResponseEntity.ok(
                applicationService.updateApplicationStatus(
                        id, employerId, req.getStatus()
                )
        );
    }

    // Candidate withdraws application
    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<ApplicationResponse> withdrawApplication(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody WithdrawApplicationRequest req
    ) throws Exception {

        return ResponseEntity.ok(
                applicationService.withdrawnApplication(
                        id, candidateId, req
                )
        );
    }

    // Employer toggles application star
    @PatchMapping("/{id}/star")
    public ResponseEntity<ApplicationResponse> toggleStar(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {

        return ResponseEntity.ok(
                applicationService.toggleStar(id, employerId)
        );
    }

//    // Get application entity (internal use)
//    @GetMapping("/{id}/entity")
//    public ResponseEntity<Application> getApplicationEntity(
//            @PathVariable Long id
//    ) throws Exception {
//
//        return ResponseEntity.ok(
//                applicationService.getApplicationEntity(id)
//        );
//    }

    // Candidate deletes application
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteApplication(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        applicationService.deleteApplication(id, candidateId);

        return ResponseEntity.ok(new ApiResponse("Application deleted successfully",true));
    }

}
