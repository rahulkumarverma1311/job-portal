package com.rahul.job.service.impl;

import com.rahul.job.dto.JobCategoryResponse;
import com.rahul.job.mapper.JobCategoryMapper;
import com.rahul.job.model.JobCategory;
import com.rahul.job.payload.JobCategoryRequest;
import com.rahul.job.repository.JobCategoryRepository;
import com.rahul.job.service.JobCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    @Override
    public JobCategoryResponse createCategory(JobCategoryRequest req) throws Exception {

        if(jobCategoryRepository.existsByName(req.getName())){
            throw new Exception("Category name already exists,choose diffrent name");
        }
        JobCategory parent=null;
        if(req.getParentId() != null){
            parent = getCategoryEntityById(req.getParentId());
        }

        String slug = generateUniqueSlug(req.getName());
        JobCategory category = JobCategory.builder()
                .name(req.getName())
                .slug(slug)
                .description(req.getDescription())
                .iconUrl(req.getIconUrl())
                .parent(parent)
                .createAt(LocalDateTime.now())
                .active(true)
                .build();

        JobCategory saved = jobCategoryRepository.save(category);
        return JobCategoryMapper.toJobCategoryResponse(saved,true);
    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {
        return jobCategoryRepository.findByActiveTrue().stream().map(c->JobCategoryMapper.toJobCategoryResponse(c,false)).toList();
    }

    @Override
    public JobCategoryResponse getCategoryById(Long id) throws Exception {
        JobCategory categoryEntityById = getCategoryEntityById(id);
        return JobCategoryMapper.toJobCategoryResponse(categoryEntityById,true);
    }

    @Override
    public JobCategoryResponse updateCategory(Long id, JobCategoryRequest req) throws Exception {
        JobCategory category = getCategoryEntityById(id);

        if (!category.getName().equals(req.getName())
                && jobCategoryRepository.existsByName(req.getName())) {
            throw new Exception("Category name already exists, choose a different name");
        }

        JobCategory parent = null;

        if (req.getParentId() != null) {
            if (req.getParentId().equals(id)) {
                throw new Exception("A category cannot be its own parent");
            }

            parent = getCategoryEntityById(req.getParentId());
        }
        category.setName(req.getName());
        category.setDescription(req.getDescription());
        category.setIconUrl(req.getIconUrl());
        category.setParent(parent);
        JobCategory save = jobCategoryRepository.save(category);

        return JobCategoryMapper.toJobCategoryResponse(save,true);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        JobCategory categoryEntityById = getCategoryEntityById(id);
        categoryEntityById.setActive(false);
        jobCategoryRepository.save(categoryEntityById);
    }

    @Override
    public JobCategory getCategoryEntityById(Long id) throws Exception {
        return jobCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Job Category not found"));
    }


    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!jobCategoryRepository.existsBySlug(base)) {
            return base;
        }

        int counter = 1;

        while (jobCategoryRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }
}
