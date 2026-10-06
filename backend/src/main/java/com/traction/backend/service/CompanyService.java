package com.traction.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.traction.backend.dto.CompanyRequest;
import com.traction.backend.dto.CompanyResponse;
import com.traction.backend.entity.Company;
import com.traction.backend.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponse createCompany(Integer founderId, CompanyRequest request) {

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

        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setId(savedCompany.getId());
        companyResponse.setFounderId(savedCompany.getFounderId());
        companyResponse.setName(savedCompany.getName());
        companyResponse.setVerificationStatus(savedCompany.getVerificationStatus());

        return companyResponse;
    }

    public CompanyResponse getCompany(Integer id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Company Doesn't exists"
            );
        }

        Company company = companyOptional.get();

        if(!"VERIFIED".equals(company.getVerificationStatus())) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Company Doesn't Exists"
            );
        }
            
        CompanyResponse response = new CompanyResponse();

        response.setId(company.getId());
        response.setName(company.getName());
        response.setCategory(company.getCategory());
        response.setField(company.getField());
        response.setWebsite(company.getWebsite());
        response.setFounderId(company.getFounderId());
        response.setVerificationStatus(company.getVerificationStatus());

        return response;
    }

    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findByVerificationStatus("VERIFIED");
        List<CompanyResponse> responses = new ArrayList<>();

        for(Company company:companies) {

            CompanyResponse response = new CompanyResponse();

            response.setId(company.getId());
            response.setFounderId(company.getFounderId());
            response.setName(company.getName());
            response.setCategory(company.getCategory());
            response.setField(company.getField());
            response.setWebsite(company.getWebsite());
            response.setVerificationStatus(company.getVerificationStatus());

            responses.add(response);
        }

        return responses;
    }

    public List<CompanyResponse> searchCompanies(
        String name,
        String field,
        String category,
        String location) {
        List<Company> companies = companyRepository.searchCompanies(name,category,location,field);
        List<CompanyResponse> responses = new  ArrayList<>();

        for(Company company:companies) {

            CompanyResponse response = new CompanyResponse();

            response.setId(company.getId());
            response.setFounderId(company.getFounderId());
            response.setName(company.getName());
            response.setCategory(company.getCategory());
            response.setField(company.getField());
            response.setWebsite(company.getWebsite());
            response.setVerificationStatus(company.getVerificationStatus());

            responses.add(response);
        }

        return responses;
    }

    public CompanyResponse updateCompany(Integer id, CompanyRequest request) {

        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Company doesn't exist");
        }

        Company company = companyOptional.get();

        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setCategory(request.getCategory());
        company.setField(request.getField());
        company.setDescription(request.getDescription());
        company.setWebsite(request.getWebsite());

        Company updatedCompany = companyRepository.save(company);

        CompanyResponse response = new CompanyResponse();

        response.setId(updatedCompany.getId());
        response.setFounderId(updatedCompany.getFounderId());
        response.setName(updatedCompany.getName());
        response.setCategory(updatedCompany.getCategory());
        response.setField(updatedCompany.getField());
        response.setWebsite(updatedCompany.getWebsite());
        response.setVerificationStatus(updatedCompany.getVerificationStatus());

        return response;
    }

    public void deleteCompany(Integer id) {

        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Company doesn't exist"
            );
        }

        companyRepository.delete(companyOptional.get());
    }


}