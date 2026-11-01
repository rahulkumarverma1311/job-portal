package com.rahul.job.mapper;

import com.rahul.job.dto.ApplicationResponse;
import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.dto.JobResponse;
import com.rahul.job.model.Application;
import com.rahul.job.payload.AddApplicationRequest;
import com.rahul.job.response.UserResponse;

public class ApplicationMapper {

    public static ApplicationResponse toResponse(Application application,
//                                                 List<ApplicationNote> notes,
                                                 JobResponse job,
                                                 CompanyResponse company,
                                                 UserResponse candidate,
//                                                 ApplicationScreening screening)
                                                 {

        return ApplicationResponse.builder()
                .id(application.getId())
                .candidate(candidate)
                .employerId(application.getEmployerId())
                .jobResponse(job)
                .companyResponse(company)
                .status(application.getStatus())

                .resumeId(application.getResumeId())
                .coverLetter(application.getCoverLetter())

                .expectedSalary(application.getExpectedSalary())

                .availableFrom(application.getAvailableFrom())
                .isStarred(application.getIsStarred())

//                .notes(toNoteResponseList(notes))
                .withdrawnAt(application.getWithdrawnAt())
                .withdrawnReason(application.getWithdrawnReason())
                .appliedAt(application.getCreateAt())
                .updatedAt(application.getUpdatedAt())
//                .screening(toScreeningResponse(screening))
                .build();
    }


    public static Application toApplicationEntity(AddApplicationRequest req,Long candidateId,Long companyId,Long employerId){
        if(req == null) return null;
        return Application.builder()
                .candidateId(candidateId)
                .jobId(req.getJobId())
                .companyId(companyId)
                .employerId(employerId)
                .resumeId(req.getResumeId())
                .coverLetter(req.getCoverLetter())
                .expectedSalary(req.getExpectedSalary())
                .availableFrom(req.getAvailableFrom())
                .build()

    }
}
