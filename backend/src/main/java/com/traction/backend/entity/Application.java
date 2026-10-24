
package com.traction.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "opportunity_applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "opportunity_id", nullable = false)
    private Integer opportunityId;

    @Column(name = "investor_id", nullable = false)
    private Integer investorId;

    @Column(nullable = false, length = 2000)
    private String message;

    @Column(nullable = false)
    private String status = "PENDING";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
