
package com.traction.backend.controller;

import com.traction.backend.dto.ApplicationRequest;
import com.traction.backend.dto.ApplicationResponse;
import com.traction.backend.service.ApplicationService;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponse apply(
            Authentication authentication,
            @Valid @RequestBody ApplicationRequest request) {

        Integer investorId = (Integer) authentication.getPrincipal();
        return applicationService.apply(investorId, request);
    }

    @GetMapping("/me")
    public List<ApplicationResponse> getMyApplications(
            Authentication authentication) {

        Integer investorId = (Integer) authentication.getPrincipal();
        return applicationService.getMyApplications(investorId);
    }

    @GetMapping("/company/{companyId}")
    public List<ApplicationResponse> getCompanyApplications(
            Authentication authentication,
            @PathVariable Integer companyId) {

        Integer founderId = (Integer) authentication.getPrincipal();

        return applicationService.getCompanyApplications(
                founderId, companyId);
    }

    @PatchMapping("/{applicationId}/status")
    public ApplicationResponse updateApplicationStatus(
            Authentication authentication,
            @PathVariable Integer applicationId,
            @RequestParam String status) {

        Integer founderId = (Integer) authentication.getPrincipal();

        return applicationService.updateApplicationStatus(
                founderId, applicationId, status.toUpperCase());
    }
}
