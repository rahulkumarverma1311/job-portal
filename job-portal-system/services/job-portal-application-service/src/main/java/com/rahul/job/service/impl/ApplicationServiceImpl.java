package com.rahul.job.service.impl;

import com.rahul.job.domain.ApplicationStatus;
import com.rahul.job.dto.ApplicationResponse;
import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.dto.JobResponse;
import com.rahul.job.mapper.ApplicationMapper;
import com.rahul.job.model.Application;
import com.rahul.job.payload.AddApplicationRequest;
import com.rahul.job.payload.CompanyApplicationFilterRequest;
import com.rahul.job.payload.WithdrawApplicationRequest;
import com.rahul.job.repository.ApplicationRepository;
import com.rahul.job.repository.ApplicationSpecification;
import com.rahul.job.response.UserResponse;
import com.rahul.job.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {


    private final ApplicationRepository applicationRepository;


    @Override
    public ApplicationResponse createApplication(Long candidateId, AddApplicationRequest req) throws Exception {
        if(applicationRepository.existsByCandidateIdAndJobId(candidateId,req.getJobId())){
            throw new Exception("you have already applied");
        }
        Long companyId =1L;
        Long employeeID =1L;
        // todo: fetch job
        //todo: fetch resume from resume service

        Application application = ApplicationMapper.toApplicationEntity(req,candidateId,companyId,employeeID);
        Application save = applicationRepository.save(application);

        // todo: call ai screening run in backgraund thread no callback needed

        return buildFullApplicationResponse(save) ;
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) throws Exception {
        Application applicationEntity = getApplicationEntity(id);
        return buildFullApplicationResponse(applicationEntity);
    }

    @Override
    public List<ApplicationResponse> getMyApplication(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId).stream().map( this::buildFullApplicationResponse).toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationForCompany(Long userId, CompanyApplicationFilterRequest req) {

        // todo: fetch company by owner id
        Long companyId =1L;
        Sort sort = buildSort(req.getSortBy());
        return applicationRepository.findAll(
                ApplicationSpecification.forCompanyWithFilters(
                        companyId,
                        req.getJobId(),
                        req.getStatus(),
                        req.getIsStarred(),
                        req.getAiShortListStatus(),
                        req.getMinAiScore()),sort)
                .stream().map( this::buildFullApplicationResponse).toList();
    }



    @Override
    public List<ApplicationResponse> getApplicationForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId).stream().map( this::buildFullApplicationResponse).toList();
    }

    @Override
    public ApplicationResponse updateApplicationStatus(Long applicationId, Long employerId, ApplicationStatus status) throws Exception {

        Application application = getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        if(application.getStatus().equals(ApplicationStatus.WITHDRAWN)){
            throw new Exception("Candidate have already Withdrawn the application");
        }
        application.setStatus(status);
        Application save = applicationRepository.save(application);

        return buildFullApplicationResponse(save);
    }



    @Override
    public ApplicationResponse withdrawnApplication(Long applicationId, Long candidateId, WithdrawApplicationRequest req) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertCandidate(application,candidateId);
        if(application.getStatus().equals(ApplicationStatus.WITHDRAWN)){
            throw new Exception("Application is already Withdrawn");
        }

        application.setStatus(ApplicationStatus.WITHDRAWN);
        application.setWithdrawnReason(req.getReason());
        Application save = applicationRepository.save(application);
        return buildFullApplicationResponse(save);
    }



    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        application.setIsStarred(!application.getIsStarred());

        Application save = applicationRepository.save(application);
        return buildFullApplicationResponse(save);
    }

    @Override
    public Application getApplicationEntity(Long id) throws Exception {
        return applicationRepository.findById(id).orElseThrow(()-> new Exception("Application not found"));
    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertCandidate(application,candidateId);
        applicationRepository.delete(application);

    }


    public ApplicationResponse buildFullApplicationResponse(Application application){

        // todo: fetch read data from services
        JobResponse jobResponse = JobResponse.builder().id(application.getId()).build();
        CompanyResponse companyResponse = CompanyResponse.builder().id(application.getId()).build();
        UserResponse userResponse = UserResponse.builder().id(application.getId()).build();
        return ApplicationMapper.toResponse(application,jobResponse,companyResponse,userResponse);
    }

    private Sort buildSort(String sortBy) {
        if(sortBy.equals("AI_SCORE_DESC"))
            return  Sort.by(Sort.Order.desc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        else if(sortBy.equals("AI_SCORE_ASC"))
            return  Sort.by(Sort.Order.asc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        return Sort.by(Sort.Direction.DESC,"appliedAt");
    }
    private void assertEmployer(Application application, Long employerId) throws Exception {
        if(!application.getEmployerId().equals(employerId))
            throw new Exception("You are not the employer for this application");
    }

    private void assertCandidate(Application application, Long candidateId) throws Exception {
        if(!application.getCandidateId().equals(candidateId)){
            throw new Exception("You are not the owner of this application");
        }
    }
}
