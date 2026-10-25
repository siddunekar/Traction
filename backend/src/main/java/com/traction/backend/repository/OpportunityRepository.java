
package com.traction.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.traction.backend.entity.Opportunity;

public interface OpportunityRepository
        extends JpaRepository<Opportunity, Integer> {

    List<Opportunity> findByCompanyId(Integer companyId);

    List<Opportunity> findByCompanyIdAndStatusIgnoreCase(
    Integer companyId, String status);

    @Query("""
        SELECT o FROM Opportunity o
        JOIN Company c ON c.id = o.companyId
        WHERE UPPER(o.status) = 'OPEN'
        AND UPPER(c.verificationStatus) = 'VERIFIED'
        """)
    List<Opportunity> findOpenOpportunitiesFromVerifiedCompanies();
}