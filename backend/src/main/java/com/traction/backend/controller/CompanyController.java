package com.traction.backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.traction.backend.dto.CompanyRequest;
import com.traction.backend.dto.CompanyResponse;
import com.traction.backend.service.CompanyService;

import jakarta.validation.Valid;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies/{founderId}")
    public CompanyResponse createCompany(
            @PathVariable Integer founderId,
            @Valid @RequestBody CompanyRequest request) {

        return companyService.createCompany(founderId, request);
    }

    @GetMapping("/companies/{id}")
    public CompanyResponse getCompany(@PathVariable Integer id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/companies")
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/companies/{name}") 
    public CompanyResponse getCompany(@PathVariable String name) {
        return companyService.getCompany(name);
    }

}