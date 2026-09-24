package com.traction.backend.controller;

import com.traction.backend.dto.CompanyRequest;
import com.traction.backend.entity.Company;
import com.traction.backend.service.CompanyService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies/{founderId}")
    public Company createCompany(
            @PathVariable Long founderId,
            @Valid @RequestBody CompanyRequest request) {

        return companyService.createCompany(founderId, request);
    }
}