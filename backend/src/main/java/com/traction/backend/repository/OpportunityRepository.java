
package com.traction.backend.repository;

import com.traction.backend.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpportunityRepository
        extends JpaRepository<Opportunity, Integer> {

    List<Opportunity> findByCompanyId(Integer companyId);
}
