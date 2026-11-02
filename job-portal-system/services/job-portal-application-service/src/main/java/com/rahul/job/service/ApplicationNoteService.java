package com.rahul.job.service;

import com.rahul.job.dto.ApplicationNoteResponse;
import com.rahul.job.payload.AddApplicationNoteRequest;

import java.util.List;

public interface ApplicationNoteService {
    ApplicationNoteResponse addApplicationNote(Long applicationId, Long employeeId, AddApplicationNoteRequest req) throws Exception;

    List<ApplicationNoteResponse> getNoteByApplication(Long applicationId,Long employeId);

    void deleteNote(Long applicationId,Long noteId,Long employeeId) throws Exception;
}
