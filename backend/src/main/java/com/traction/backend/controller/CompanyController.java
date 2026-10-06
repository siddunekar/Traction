package com.traction.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

 
    @GetMapping("/companies/search")
    public List<CompanyResponse> searchCompanies(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String location,
        @RequestParam(required = false) String field
    ) {
        return companyService.searchCompanies(name,category,location,field);
    }

    @PutMapping("/companies/{id}")
    public CompanyResponse updateCompany(
        @PathVariable Integer id,
        @Valid @RequestBody CompanyRequest request) {

        return companyService.updateCompany(id, request);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

}