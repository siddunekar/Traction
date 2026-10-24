
package com.traction.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "company_opportunities")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "opportunity_type", nullable = false)
    private String opportunityType;

    @Column(name = "funding_amount")
    private BigDecimal fundingAmount;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(String opportunityType) {
        this.opportunityType = opportunityType;
    }

    public BigDecimal getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(BigDecimal fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}