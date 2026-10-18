package com.rahul.job.service.impl;

import com.rahul.job.dto.JobTagResponse;
import com.rahul.job.mapper.JobTagMapper;
import com.rahul.job.model.JobTag;
import com.rahul.job.payload.JobTagRequest;
import com.rahul.job.repository.JobTagRepository;
import com.rahul.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {

    private final JobTagRepository jobTagRepository;
    @Override
    public JobTagResponse createTag(JobTagRequest req) throws Exception {
        if(jobTagRepository.existsByName(req.getName())){
            throw new Exception("Job Tag Already exists with name");
        }
        String slug = generateUniqueSlug(req.getName());
        JobTag tag = JobTag.builder()
                .name(req.getName())
                .slug(slug)
                .createAt(LocalDateTime.now())
                .build();
        JobTag save = jobTagRepository.save(tag);

        return JobTagMapper.convertToJobTagResponse(save);
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        return jobTagRepository.findAll().stream().map(JobTagMapper::convertToJobTagResponse).toList();
    }

    @Override
    public JobTagResponse getById(Long id) throws Exception {
        JobTag tag = jobTagRepository.findById(id).orElseThrow(() -> new Exception("JobTag not found"));
        return JobTagMapper.convertToJobTagResponse(tag);
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest jobTagRequest) throws Exception {
        JobTag jobTag = getJobTagEntityById(id);
        if(jobTag.getName() != jobTagRequest.getName() && jobTagRepository.existsByName(jobTagRequest.getName())){
            throw new Exception("JobTag name is already present");
        }
        jobTag.setName(jobTagRequest.getName());
        return JobTagMapper.convertToJobTagResponse(jobTagRepository.save(jobTag));
    }

    @Override
    public void deleteTag(Long id) throws Exception {
        JobTag tag = jobTagRepository.findById(id).orElseThrow(() -> new Exception("JobTag not found"));
        jobTagRepository.delete(tag);
    }

    @Override
    public JobTag getJobTagEntityById(Long id) throws Exception {
        JobTag tag = jobTagRepository.findById(id).orElseThrow(() -> new Exception("JobTag not found"));
        return tag;
    }

    @Override
    public Set<JobTag> getJobTagEntitiesByIds(Set<Long> ids) throws Exception {
        Set<JobTag> jobTags = new HashSet<>(jobTagRepository.findAllById(ids));
        return jobTags;
    }


    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!jobTagRepository.existsBySlug(base)) {
            return base;
        }

        int counter = 1;

        while (jobTagRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }
}
