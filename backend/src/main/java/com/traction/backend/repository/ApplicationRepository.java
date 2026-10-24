
package com.traction.backend.repository;

import com.traction.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Integer> {

    List<Application> findByOpportunityId(Integer opportunityId);

    List<Application> findByInvestorId(Integer investorId);

    boolean existsByOpportunityIdAndInvestorId(
            Integer opportunityId, Integer investorId);

    List<Application> findByOpportunityIdIn(List<Integer> opportunityIds);
}
