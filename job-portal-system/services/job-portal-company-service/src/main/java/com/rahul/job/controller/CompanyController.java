package com.rahul.job.controller;

import com.rahul.job.domain.CompanyStatus;
import com.rahul.job.domain.CompanyType;
import com.rahul.job.domain.IndustryType;
import com.rahul.job.dto.ApiResponse;
import com.rahul.job.dto.CompanyRequest;
import com.rahul.job.dto.CompanyResponse;
import com.rahul.job.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {


    private final CompanyService companyService;

    @PostMapping()
    public ResponseEntity<CompanyResponse> createCompany(@RequestHeader("X-User-Id") Long ownerId, @RequestBody @Valid CompanyRequest companyRequest) throws Exception {
        CompanyResponse company = companyService.createCompany(ownerId, companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) throws Exception {
        CompanyResponse company = companyService.getCompanyById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }


    @GetMapping("/my")
    public ResponseEntity<CompanyResponse> getMyCompany(@RequestHeader("X-User-Id") Long ownerId) throws Exception {
        CompanyResponse company = companyService.getMyCompany(ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompany(
            @RequestParam(required = false) CompanyType companyType,
            @RequestParam(required = false) IndustryType industryType,
            @RequestParam(required = false) CompanyStatus companyStatus
    ) {
        return ResponseEntity.ok(companyService.getAllCompany(companyType, industryType, companyStatus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long companyId,
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest req
    ) throws Exception {
        return ResponseEntity.ok(
                companyService.updateCompany(ownerId, req, companyId)
        );
    }


    @PatchMapping("/{id}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(
                companyService.verifyCompany(id)
        );
    }


    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(
                companyService.deactivateCompany(id)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(Long id, @RequestHeader("X-User-Id") Long ownerId) throws Exception {
        companyService.deleteCompany(id, ownerId);
        return ResponseEntity.ok(new ApiResponse("company deleted successfully", true));

    }
}
