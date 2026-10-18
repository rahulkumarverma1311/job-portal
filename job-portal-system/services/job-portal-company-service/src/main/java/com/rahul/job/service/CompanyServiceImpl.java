package com.rahul.job.service;

import com.rahul.job.domain.CompanyStatus;
import com.rahul.job.domain.CompanyType;
import com.rahul.job.domain.IndustryType;
import com.rahul.job.dto.CompanyRequest;
import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.dto.SocialLinkResponse;
import com.rahul.job.entity.Company;
import com.rahul.job.entity.SocialLink;
import com.rahul.job.mapper.CompanyMapper;
import com.rahul.job.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception {
        if (companyRepository.existsByOwnerId(ownerId)) {
            throw new Exception("You already have company registered");
        }
        if (companyRepository.existsByName(request.getName())) {
            throw new Exception("Please choose different name");
        }


        if (request.getRegistrationNumber() != null && companyRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new Exception("company already exist by this registration number please check");
        }
        String slug = generateUniqueSlug(request.getName());


        Company company = Company.builder()
                .name(request.getName())
                .slug(slug)
                .companyType(request.getCompanyType())
                .companySize(request.getCompanySize())
                .createAt(LocalDateTime.now())
                .active(true)
                .coverImageUrl(request.getCoverImageUrl())
                .description(request.getDescription())
                .foundedYear(request.getFoundedYear())
                .website(request.getWebsite())
                .industryType(request.getIndustryType())
                .ownerId(ownerId)
                .logoUrl(request.getLogoUrl())
                .email(request.getEmail())
                .phone(request.getPhone())
                .socialLinks(mapSociealLinks(request.getSocialLinks()))
                .registrationNumber(request.getRegistrationNumber())
                .tegLine(request.getTagline())
                .build();


        Company saved = companyRepository.save(company);
        return CompanyMapper.toResponse(saved);
    }

    private List<SocialLink> mapSociealLinks(List<SocialLinkResponse> socialLinks) {
        if (socialLinks == null || socialLinks.isEmpty()) {
            return new ArrayList<>();
        }

        return socialLinks.stream().map(
                e -> SocialLink.builder().platformName(e.getPlatformName()).url(e.getUrl()).build()).collect(Collectors.toList());
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!companyRepository.existsBySlug(base)) {
            return base;
        }

        int counter = 1;

        while (companyRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company company = companyRepository.findById(id).orElseThrow(() -> new Exception("company not found with id : " + id));
        return CompanyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        Company company = companyRepository.findByOwnerId(ownerId).orElseThrow(() -> new Exception("company not found with ownerId : " + ownerId));
        return CompanyMapper.toResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompany(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return companyRepository.findByFilters(companyType, industryType, companyStatus).stream().map(CompanyMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public CompanyResponse updateCompany(Long ownerId, CompanyRequest companyRequest, Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        if (!company.getName().equals(companyRequest.getName())) {
            if (companyRepository.existsByName(companyRequest.getName())) {
                throw new Exception("name is already exist in system please choose different name");
            }
        }

        if (companyRequest.getRegistrationNumber() != null && !company.getRegistrationNumber().equals(companyRequest.getRegistrationNumber()) && companyRepository.existsByRegistrationNumber(companyRequest.getRegistrationNumber())) {
            throw new Exception("company already exist by this registration number please check");
        }

        company.setName(companyRequest.getName());
        company.setTegLine(companyRequest.getTagline());
        company.setDescription(companyRequest.getDescription());
        company.setLogoUrl(companyRequest.getLogoUrl());
        company.setCoverImageUrl(companyRequest.getCoverImageUrl());
        company.setWebsite(companyRequest.getWebsite());
        company.setEmail(companyRequest.getEmail());
        company.setPhone(companyRequest.getPhone());
        company.setFoundedYear(companyRequest.getFoundedYear());
        company.setCompanySize(companyRequest.getCompanySize());
        company.setCompanyType(companyRequest.getCompanyType());
        company.setIndustryType(companyRequest.getIndustryType());
        company.setRegistrationNumber(companyRequest.getRegistrationNumber());
        company.setSocialLinks(mapSociealLinks(companyRequest.getSocialLinks()));


        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus(CompanyStatus.ACTIVE);
        company.setVerified(true);
        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        assertOwner(company, ownerId);
        companyRepository.delete(company);
    }

    private void assertOwner(Company company, Long ownerId) throws Exception {
        if (!company.getOwnerId().equals(ownerId)) {
            throw new Exception("you are not the owner of this company");
        }
    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus(CompanyStatus.SUSPENDED);
        company.setVerified(false);
        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public Company getCompanyEntityById(Long id) throws Exception {
        return companyRepository.findById(id).orElseThrow(() -> new Exception("company not found with id : " + id));
    }
}
