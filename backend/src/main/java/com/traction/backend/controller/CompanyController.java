package com.traction.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

   @PostMapping("/companies")
   public CompanyResponse createCompany(
           Authentication authentication,
           @Valid @RequestBody CompanyRequest request) {
       Integer founderId = (Integer) authentication.getPrincipal();
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
            Authentication authentication,
            @Valid @RequestBody CompanyRequest request) {

            Integer founderId = (Integer) authentication.getPrincipal();

        return companyService.updateCompany(id, founderId, request);
    }   

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(
            @PathVariable Integer id,
            Authentication authentication) {

        Integer founderId = (Integer) authentication.getPrincipal();

        companyService.deleteCompany(id, founderId);

        return ResponseEntity.noContent().build();
    }
}