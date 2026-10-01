package com.traction.backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traction.backend.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findByVerificationStatus(String varificationStatus);
}