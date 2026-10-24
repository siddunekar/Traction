
package com.traction.backend.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traction.backend.dto.OpportunityRequest;
import com.traction.backend.dto.OpportunityResponse;
import com.traction.backend.service.OpportunityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    
    @PostMapping
    public OpportunityResponse createOpportunity(
            Authentication authentication,
            @Valid @RequestBody OpportunityRequest request) {

            Integer founderId = (Integer) authentication.getPrincipal();

        return opportunityService.createOpportunity(founderId, request);
    }


    @GetMapping
    public List<OpportunityResponse> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }

    @GetMapping("/company/{companyId}")
    public List<OpportunityResponse> getCompanyOpportunities(
            @PathVariable Integer companyId) {

        return opportunityService.getCompanyOpportunities(companyId);
    }
}
