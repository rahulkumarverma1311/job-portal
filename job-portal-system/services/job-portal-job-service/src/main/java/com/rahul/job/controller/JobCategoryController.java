package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.JobCategoryResponse;
import com.rahul.job.payload.JobCategoryRequest;
import com.rahul.job.service.JobCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-categories")
@RequiredArgsConstructor
public class JobCategoryController {

    private final JobCategoryService jobCategoryService;

    @PostMapping
    public ResponseEntity<JobCategoryResponse> createCategory(
            @Valid @RequestBody JobCategoryRequest req) throws Exception {

        JobCategoryResponse response =
                jobCategoryService.createCategory(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<JobCategoryResponse>> getAllCategories() {

        List<JobCategoryResponse> categories =
                jobCategoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    // Get a category by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> getCategoryById(
            @PathVariable Long id) throws Exception {

        JobCategoryResponse response =
                jobCategoryService.getCategoryById(id);

        return ResponseEntity.ok(response);
    }

    // Update an existing category
    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody JobCategoryRequest req) throws Exception {

        JobCategoryResponse response =
                jobCategoryService.updateCategory(id, req);

        return ResponseEntity.ok(response);
    }

    // Soft-delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable Long id) throws Exception {

        jobCategoryService.deleteCategory(id);

        return ResponseEntity.ok(new ApiResponse("Job Category Deleted Successfully",true));
    }


}
