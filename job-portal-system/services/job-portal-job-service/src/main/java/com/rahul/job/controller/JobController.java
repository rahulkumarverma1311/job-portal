package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.JobRequest;
import com.rahul.job.dto.JobResponse;
import com.rahul.job.payload.JobSearchRequest;
import com.rahul.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/jobs")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse>createJob(@RequestHeader("X-User-Id") Long employeeId, @RequestBody @Valid JobRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(employeeId,request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse>getJobById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
    @GetMapping
    public ResponseEntity<List<JobResponse>>getJobs(@ModelAttribute JobSearchRequest request){
        return ResponseEntity.ok(jobService.getJobs(request));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobsByCompany(@PathVariable Long companyId){
        return ResponseEntity.ok(jobService.getJobsByCompany(companyId));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<JobResponse>>getJobsAdmin(){
        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse>updateJob(@PathVariable Long id,@RequestHeader("X-User-Id") Long employeeId,@RequestBody @Valid JobRequest request) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(id,employeeId,request));
    }
    @PatchMapping("/publish/{jobId}")
    public ResponseEntity<JobResponse>publishJob(@PathVariable Long jobId,@RequestHeader("X-User-Id") Long employeeId) throws Exception {
        return ResponseEntity.ok(jobService.publishJob(jobId,employeeId));
    }

    @PatchMapping("/close/{jobId}")
    public ResponseEntity<JobResponse>closeJob(@PathVariable Long jobId,@RequestHeader("X-User-Id") Long employeeId) throws Exception {
        return ResponseEntity.ok(jobService.closeJob(jobId,employeeId));
    }


    @DeleteMapping("/{jobId}")
    public ResponseEntity<ApiResponse>deleteJob(@PathVariable Long jobId,@RequestHeader("X-User-Id") Long employeeId) throws Exception {
        jobService.deleteJob(jobId,employeeId);
        return ResponseEntity.ok(new ApiResponse("Job Deleted Successfully",true));
    }
}
