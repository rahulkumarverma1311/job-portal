package com.rahul.job.mapper;

import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.dto.SocialLinkResponse;
import com.rahul.job.entity.Company;
import com.rahul.job.entity.SocialLink;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CompanyMapper {


    public static CompanyResponse toResponse(Company company){

        List<SocialLinkResponse>socialLink  = company.getSocialLinks() == null ? Collections.emptyList() : company.getSocialLinks().stream().map(CompanyMapper::toSocialLinkResponse).toList();
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .tagline(company.getTegLine())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .website(company.getWebsite())
                .email(company.getEmail())
                .phone(company.getPhone())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .status(company.getStatus())
                .active(company.isActive())
                .ownerId(company.getOwnerId())
                .socialLinks(socialLink)
                .createdAt(company.getCreateAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }

    private static SocialLinkResponse toSocialLinkResponse(SocialLink socialLink) {
        return SocialLinkResponse.builder().platformName(socialLink.getPlatformName()).url(socialLink.getUrl()).build();
    }
}
