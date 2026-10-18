package com.rahul.job.service.impl;

import com.rahul.job.dto.JobSkillResponse;
import com.rahul.job.mapper.JobSkillMapper;
import com.rahul.job.model.JobSkill;
import com.rahul.job.payload.JobSkillRequest;
import com.rahul.job.repository.JobSkillRepository;
import com.rahul.job.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepository jobSkillRepository;


    @Override
    public JobSkillResponse createJobSkill(JobSkillRequest request) throws Exception {

        if(jobSkillRepository.existsByName(request.getName())){
            throw new Exception("skill is already exist with this name please choose diffrent skill name");
        }
        String slug = generateUniqueSlug(request.getName());

        JobSkill skill = JobSkill.builder()
                .name(request.getName())
                .category(request.getCategory())
                .slug(slug)
                .createAt(LocalDateTime.now())
                .active(true)
                .build();
        JobSkill save = jobSkillRepository.save(skill);


        return JobSkillMapper.convertToResponse(skill);
    }

    @Override
    public List<JobSkillResponse> getAllJobSkills() {
        return jobSkillRepository.findByActiveTrue().stream().map(JobSkillMapper::convertToResponse).toList();
    }

    @Override
    public JobSkillResponse getById(Long id) throws Exception {
        JobSkill skill = jobSkillRepository.findById(id).orElseThrow(() -> new Exception("JobSkill not found"));
        return JobSkillMapper.convertToResponse(skill);
    }

    @Override
    public JobSkillResponse updateJobSkill(Long id, JobSkillRequest req) throws Exception {
        JobSkill skill = jobSkillRepository.findById(id).orElseThrow(() -> new Exception("JobSkill not found"));
        if(req.getName() != skill.getName() && jobSkillRepository.existsByName(req.getName())){
            throw new Exception("Skill is already exist with this name please choose different name");

        }
        skill.setName(req.getName());
        skill.setCategory(req.getCategory());
        JobSkill save = jobSkillRepository.save(skill);
        return JobSkillMapper.convertToResponse(save);
    }

    @Override
    public void deleteJobSkill(Long id) throws Exception {
        JobSkill skill = jobSkillRepository.findById(id).orElseThrow(() -> new Exception("JobSkill not found"));
        skill.setActive(false);
        jobSkillRepository.save(skill);

    }

    @Override
    public Set<JobSkill> getSkillsByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }

        return new HashSet<>(jobSkillRepository.findAllById(ids));
    }



    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!jobSkillRepository.existsBySlug(base)) {
            return base;
        }

        int counter = 1;

        while (jobSkillRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }
}
