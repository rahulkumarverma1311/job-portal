package com.rahul.job.controller;

import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.LanguageResponse;
import com.rahul.job.payload.AddLanguageRequest;
import com.rahul.job.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;


    @PostMapping
    public ResponseEntity<LanguageResponse> addLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddLanguageRequest req
    ) throws Exception {

        LanguageResponse response =
                languageService.addLanguage(resumeId, candidateId, req);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all languages for a resume
    @GetMapping
    public ResponseEntity<List<LanguageResponse>> getAllLanguage(
            @PathVariable Long resumeId
    ) {
        return ResponseEntity.ok(
                languageService.getAllLanguage(resumeId)
        );
    }

    // Update language
    @PutMapping("/{languageId}")
    public ResponseEntity<LanguageResponse> updateLanguage(
            @PathVariable Long resumeId,
            @PathVariable Long languageId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody AddLanguageRequest req
    ) throws Exception {

        LanguageResponse response =
                languageService.updateLanguage(
                        languageId, resumeId, candidateId, req
                );

        return ResponseEntity.ok(response);
    }

    // Delete language
    @DeleteMapping("/{languageId}")
    public ResponseEntity<ApiResponse> deleteLanguage(
            @PathVariable Long resumeId,
            @PathVariable Long languageId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {

        languageService.deleteLanguage(
                languageId, resumeId, candidateId
        );

        return ResponseEntity.ok(new ApiResponse("Language deleted successfully",true));
    }
}
