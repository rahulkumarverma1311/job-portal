package com.rahul.job.controller;


import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.ApplicationNoteResponse;
import com.rahul.job.payload.AddApplicationNoteRequest;
import com.rahul.job.service.ApplicationNoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications/{applicationId}/notes")
@RequiredArgsConstructor
public class ApplicationNoteController {

    private final ApplicationNoteService applicationNoteService;

    @PostMapping
    public ResponseEntity<ApplicationNoteResponse> addApplicationNote(
            @PathVariable Long applicationId,
            @RequestHeader("X-User-Id") Long employeeId,
            @Valid @RequestBody AddApplicationNoteRequest request
    ) throws Exception {

        ApplicationNoteResponse response =
                applicationNoteService.addApplicationNote(
                        applicationId, employeeId, request
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    @GetMapping
    public ResponseEntity<List<ApplicationNoteResponse>> getApplicationNotes(
            @PathVariable Long applicationId,
            @RequestHeader("X-User-Id") Long employeeId
    ) throws Exception {

        List<ApplicationNoteResponse> response =
                applicationNoteService.getNoteByApplication(
                        applicationId, employeeId
                );

        return ResponseEntity.ok(response);
    }

    // Delete Application Note
    @DeleteMapping("/{noteId}")
    public ResponseEntity<ApiResponse> deleteApplicationNote(
            @PathVariable Long applicationId,
            @PathVariable Long noteId,
            @RequestHeader("X-User-Id") Long employeeId
    ) throws Exception {

        applicationNoteService.deleteNote(
                applicationId, noteId, employeeId
        );

        return ResponseEntity.ok(new ApiResponse("Application note deleted successfully",true));
    }

}
