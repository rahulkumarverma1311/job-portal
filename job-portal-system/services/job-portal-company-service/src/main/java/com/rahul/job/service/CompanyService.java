package com.rahul.job.service;

import com.rahul.job.domain.CompanyStatus;
import com.rahul.job.domain.CompanyType;
import com.rahul.job.domain.IndustryType;
import com.rahul.job.dto.CompanyRequest;
import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.entity.Company;

import java.util.List;

public interface CompanyService {
    CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception;
    CompanyResponse getCompanyById(Long id) throws Exception;
    CompanyResponse getMyCompany(Long ownerId) throws Exception;
    List<CompanyResponse> getAllCompany(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus);
    CompanyResponse updateCompany(Long ownerId,CompanyRequest companyRequest,Long companyId) throws Exception;
    CompanyResponse verifyCompany(Long companyId) throws Exception;
    void  deleteCompany(Long companyId,Long ownerId) throws Exception;
    CompanyResponse deactivateCompany(Long companyId) throws Exception;
    Company getCompanyEntityById(Long id) throws Exception;

}
