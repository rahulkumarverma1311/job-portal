package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.ProjectResponse;
import com.rahul.job.payload.AddProjectRequest;
import com.rahul.job.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // Add a project
    @PostMapping
    public ResponseEntity<ProjectResponse> addProject(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid  AddProjectRequest req
    ) throws Exception {

        ProjectResponse response =
                projectService.addProject(resumeId, candidateId, req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all projects for a resume
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(
            @PathVariable Long resumeId
    ) {
        return ResponseEntity.ok(
                projectService.getAllProjects(resumeId)
        );
    }

    // Update a project
    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long resumeId,
            @PathVariable Long projectId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddProjectRequest req
    ) throws Exception {

        ProjectResponse response =
                projectService.updateProject(
                        projectId, resumeId, candidateId, req
                );

        return ResponseEntity.ok(response);
    }

    // Delete a project
    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse> deleteProject(
            @PathVariable Long resumeId,
            @PathVariable Long projectId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        projectService.deleteProject(
                projectId, resumeId, candidateId
        );

        return ResponseEntity.ok(new ApiResponse("Project deleted successfully",true));
    }
}
