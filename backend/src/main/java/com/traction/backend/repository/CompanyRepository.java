package com.traction.backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.traction.backend.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findByVerificationStatus(String verificationStatus);

    @Query("""
    SELECT c FROM Company c
    WHERE c.verificationStatus = 'VERIFIED'
    AND (:category IS NULL OR LOWER(c.category) LIKE LOWER(CONCAT('%', :category, '%')))
    AND (:location IS NULL OR LOWER(c.location) LIKE LOWER(CONCAT('%', :location, '%')))
    AND (:field IS NULL OR LOWER(c.field) LIKE LOWER(CONCAT('%', :field, '%')))
    AND (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)

    List<Company> searchCompanies(
        @Param("name") String name,
        @Param("category") String category,
        @Param("location") String location,
        @Param("field") String field
    );

}