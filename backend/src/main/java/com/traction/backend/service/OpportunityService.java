
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

        if (!company.getFounderId().equals(founderId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You do not own this company");
        }

        if (!"VERIFIED".equalsIgnoreCase(company.getVerificationStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Only verified companies can post opportunities");
        }

        Opportunity opportunity = new Opportunity();
        opportunity.setCompanyId(company.getId());
        opportunity.setTitle(request.getTitle());
        opportunity.setDescription(request.getDescription());
        opportunity.setOpportunityType(request.getOpportunityType());
        opportunity.setFundingAmount(request.getFundingAmount());

        return mapToResponse(opportunityRepository.save(opportunity));
    }

    public List<OpportunityResponse> getAllOpportunities() {
        return opportunityRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<OpportunityResponse> getCompanyOpportunities(
            Integer companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Company not found");
        }

        return opportunityRepository.findByCompanyId(companyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OpportunityResponse mapToResponse(Opportunity opportunity) {
        OpportunityResponse response = new OpportunityResponse();
        response.setId(opportunity.getId());
        response.setCompanyId(opportunity.getCompanyId());
        response.setTitle(opportunity.getTitle());
        response.setDescription(opportunity.getDescription());
        response.setOpportunityType(opportunity.getOpportunityType());
        response.setFundingAmount(opportunity.getFundingAmount());
        response.setCreatedAt(opportunity.getCreatedAt());
        return response;
    }
}
