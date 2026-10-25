
package com.traction.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.traction.backend.dto.OpportunityRequest;
import com.traction.backend.dto.OpportunityResponse;
import com.traction.backend.entity.Company;
import com.traction.backend.entity.Opportunity;
import com.traction.backend.repository.CompanyRepository;
import com.traction.backend.repository.OpportunityRepository;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final CompanyRepository companyRepository;

    public OpportunityService(
            OpportunityRepository opportunityRepository,
            CompanyRepository companyRepository) {
        this.opportunityRepository = opportunityRepository;
        this.companyRepository = companyRepository;
    }

    public OpportunityResponse createOpportunity(
            Integer founderId, OpportunityRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company not found"));

        if (company.getFounderId() == null
                || !company.getFounderId().equals(founderId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You do not own this company");
        }

        if (!"VERIFIED".equalsIgnoreCase(
                company.getVerificationStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Only verified companies can post opportunities");
        }

        if (request.getMinimumAmount() != null
                && request.getMaximumAmount() != null
                && request.getMinimumAmount()
                        .compareTo(request.getMaximumAmount()) > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Minimum amount cannot exceed maximum amount");
        }

        Opportunity opportunity = new Opportunity();
        opportunity.setCompanyId(company.getId());
        opportunity.setInterestId(request.getInterestId());
        opportunity.setTitle(request.getTitle());
        opportunity.setDescription(request.getDescription());
        opportunity.setMinimumAmount(request.getMinimumAmount());
        opportunity.setMaximumAmount(request.getMaximumAmount());
        opportunity.setStatus("OPEN");

        return mapToResponse(opportunityRepository.save(opportunity));
    }

    public List<OpportunityResponse> getAllOpportunities() {
        return opportunityRepository
                .findOpenOpportunitiesFromVerifiedCompanies()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

public List<OpportunityResponse> getCompanyOpportunities(
        Integer companyId) {

    Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Company not found"));

    if (!"VERIFIED".equalsIgnoreCase(
            company.getVerificationStatus())) {
        return List.of();
    }

    return opportunityRepository
            .findByCompanyIdAndStatusIgnoreCase(companyId, "OPEN")
            .stream()
            .map(this::mapToResponse)
            .toList();
}

    private OpportunityResponse mapToResponse(Opportunity opportunity) {
        OpportunityResponse response = new OpportunityResponse();
        response.setId(opportunity.getId());
        response.setCompanyId(opportunity.getCompanyId());
        response.setInterestId(opportunity.getInterestId());
        response.setTitle(opportunity.getTitle());
        response.setDescription(opportunity.getDescription());
        response.setMinimumAmount(opportunity.getMinimumAmount());
        response.setMaximumAmount(opportunity.getMaximumAmount());
        response.setStatus(opportunity.getStatus());
        response.setCreatedAt(opportunity.getCreatedAt());
        return response;
    }
}
