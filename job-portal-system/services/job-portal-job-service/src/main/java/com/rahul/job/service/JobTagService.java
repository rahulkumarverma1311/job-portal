package com.rahul.job.service;

import com.rahul.job.dto.JobTagResponse;
import com.rahul.job.model.JobTag;
import com.rahul.job.payload.JobTagRequest;

import java.util.List;
import java.util.Set;

public interface JobTagService {

    JobTagResponse createTag(JobTagRequest req) throws Exception;
    List<JobTagResponse> getAllTags();
    JobTagResponse getById(Long id) throws Exception;
    JobTagResponse updateTag(Long id, JobTagRequest jobTagRequest) throws Exception;
    void deleteTag(Long id) throws Exception;
    JobTag getJobTagEntityById(Long id) throws Exception;
    Set<JobTag> getJobTagEntitiesByIds(Set<Long> ids) throws Exception;
}
