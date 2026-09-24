package com.traction.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.traction.backend.dto.CompanyRequest;
import com.traction.backend.entity.Company;
import com.traction.backend.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Long founderId, CompanyRequest request) {

        Company company = new Company();

        company.setFounderId(founderId);
        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setCategory(request.getCategory());
        company.setField(request.getField());
        company.setDescription(request.getDescription());
        company.setWebsite(request.getWebsite());

        company.setVerificationStatus("PENDING");
        company.setCreatedAt(LocalDateTime.now());

        Company savedCompany = companyRepository.save(company);

        return savedCompany;
    }
}