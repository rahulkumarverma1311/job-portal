package com.rahul.job.service.serviceImpl;

import com.rahul.job.dto.ProjectResponse;
import com.rahul.job.mapper.ResumeMapper;
import com.rahul.job.model.Project;
import com.rahul.job.model.Resume;
import com.rahul.job.payload.AddProjectRequest;
import com.rahul.job.repository.ProjectRepository;
import com.rahul.job.service.ProjectService;
import com.rahul.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ResumeService resumeService;

    @Override
    public ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);
        Project project = Project.builder()
                .resume(resume)
                .projectUrl(req.getProjectUrl())
                .sourceCodeUrl(req.getSourceCodeUrl())
                .title(req.getTitle())
                .descriptions(req.getDescription())
                .technologies(req.getTechnologies() !=null? req.getTechnologies():List.of())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .isOnGoing(Boolean.TRUE.equals(req.getIsOngoing()))
                .displayOrder(req.getDisplayOrder() != null? req.getDisplayOrder():0)
                .build();
        Project save = projectRepository.save(project);
        return ResumeMapper.toProjectResponse(save);
    }

    @Override
    public List<ProjectResponse> getAllProjects(Long resumeId) {
        return projectRepository.findByResume_IdOrderByDisplayOrderAse(resumeId).stream().map(ResumeMapper::toProjectResponse).toList();
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest req) throws Exception {
        Project project = getProjectEntityById(projectId);
        assertOwner(project.getResume(),candidateId);
        project.setTitle(req.getTitle());
        project.setDescriptions(req.getDescription());

        if (req.getTechnologies() != null) {
            project.setTechnologies(req.getTechnologies());
        }

        project.setProjectUrl(req.getProjectUrl());
        project.setSourceCodeUrl(req.getSourceCodeUrl());
        project.setStartDate(req.getStartDate());
        project.setEndDate(req.getEndDate());

        project.setIsOnGoing(
                Boolean.TRUE.equals(req.getIsOngoing())
        );

        if (req.getDisplayOrder() != null) {
            project.setDisplayOrder(req.getDisplayOrder());
        }

        return ResumeMapper.toProjectResponse(
                projectRepository.save(project)
        );


    }

    @Override
    public void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception {
        Resume resume = resumeService.getResumeEntityById(resumeId);
        assertOwner(resume,candidateId);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new Exception("project not found"));
        projectRepository.delete(project);
    }

    public Project getProjectEntityById(Long projectId) throws Exception {
        return projectRepository.findById(projectId).orElseThrow(() -> new Exception("project not found"));
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("resume not found with id");
        }
    }
}
