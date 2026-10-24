
package com.traction.backend.service;

import com.traction.backend.dto.ApplicationRequest;
import com.traction.backend.dto.ApplicationResponse;
import com.traction.backend.entity.Application;
import com.traction.backend.entity.Company;
import com.traction.backend.entity.Opportunity;
import com.traction.backend.repository.ApplicationRepository;
import com.traction.backend.repository.CompanyRepository;
import com.traction.backend.repository.OpportunityRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final OpportunityRepository opportunityRepository;
    private final CompanyRepository companyRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            OpportunityRepository opportunityRepository,
            CompanyRepository companyRepository) {
        this.applicationRepository = applicationRepository;
        this.opportunityRepository = opportunityRepository;
        this.companyRepository = companyRepository;
    }

    public ApplicationResponse apply(
            Integer investorId, ApplicationRequest request) {

        if (!opportunityRepository.existsById(request.getOpportunityId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Opportunity not found");
        }

        if (applicationRepository.existsByOpportunityIdAndInvestorId(
                request.getOpportunityId(), investorId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "You have already applied to this opportunity");
        }

        Application application = new Application();
        application.setOpportunityId(request.getOpportunityId());
        application.setInvestorId(investorId);
        application.setMessage(request.getMessage());

        return mapToResponse(applicationRepository.save(application));
    }

    public List<ApplicationResponse> getMyApplications(Integer investorId) {
        return applicationRepository.findByInvestorId(investorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ApplicationResponse> getCompanyApplications(
            Integer founderId, Integer companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company not found"));

        if (!company.getFounderId().equals(founderId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You do not own this company");
        }

        List<Integer> opportunityIds =
                opportunityRepository.findByCompanyId(companyId)
                        .stream()
                        .map(Opportunity::getId)
                        .toList();

        return applicationRepository
                .findByOpportunityIdIn(opportunityIds)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ApplicationResponse updateApplicationStatus(
            Integer founderId, Integer applicationId, String status) {

        if (!status.equals("ACCEPTED") && !status.equals("REJECTED")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status must be ACCEPTED or REJECTED");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Application not found"));

        Opportunity opportunity = opportunityRepository
                .findById(application.getOpportunityId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Opportunity not found"));

        Company company = companyRepository.findById(opportunity.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company not found"));

        if (!company.getFounderId().equals(founderId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You do not own this company's opportunity");
        }

        application.setStatus(status);
        return mapToResponse(applicationRepository.save(application));
    }

    private ApplicationResponse mapToResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setOpportunityId(application.getOpportunityId());
        response.setInvestorId(application.getInvestorId());
        response.setMessage(application.getMessage());
        response.setStatus(application.getStatus());
        response.setCreatedAt(application.getCreatedAt());
        return response;
    }
}
