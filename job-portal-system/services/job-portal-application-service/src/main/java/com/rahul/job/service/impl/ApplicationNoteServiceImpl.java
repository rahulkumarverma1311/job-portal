package com.rahul.job.service.impl;

import com.rahul.job.dto.ApplicationNoteResponse;
import com.rahul.job.mapper.ApplicationMapper;
import com.rahul.job.model.Application;
import com.rahul.job.model.ApplicationNote;
import com.rahul.job.payload.AddApplicationNoteRequest;
import com.rahul.job.repository.ApplicationNoteRepository;
import com.rahul.job.service.ApplicationNoteService;
import com.rahul.job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ApplicationNoteServiceImpl implements ApplicationNoteService {

    private final ApplicationNoteRepository applicationNoteRepository;
    private final ApplicationService applicationService;

    @Override
    public ApplicationNoteResponse addApplicationNote(Long applicationId, Long employeeId, AddApplicationNoteRequest req) throws Exception {

        Application application = applicationService.getApplicationEntity(applicationId);
        assertEmployer(application,employeeId);

        ApplicationNote applicationNote = ApplicationNote.builder()
                .addedByUserId(employeeId)
                .application(application)
                .content(req.getContent())
                .build();


        ApplicationNote saved = applicationNoteRepository.save(applicationNote);
        return ApplicationMapper.toNoteResponse(saved);
    }

    @Override
    public List<ApplicationNoteResponse> getNoteByApplication(Long applicationId, Long employeId) {
        return applicationNoteRepository.findByApplicationId(applicationId).stream().map(ApplicationMapper::toNoteResponse).toList();
    }

    @Override
    public void deleteNote(Long applicationId, Long noteId, Long employeeId) throws Exception {
        Application application = applicationService.getApplicationEntity(applicationId);
        assertEmployer(application,employeeId);
        ApplicationNote applicationNote = applicationNoteRepository.findById(noteId).orElseThrow(() -> new Exception("Application Note not found"));
        applicationNoteRepository.delete(applicationNote);
    }



    private void assertEmployer(Application application, Long employeeId) throws Exception {
        if(!application.getEmployerId().equals(employeeId)){
            throw new Exception("you are not employer for this application");
        }
    }
}
