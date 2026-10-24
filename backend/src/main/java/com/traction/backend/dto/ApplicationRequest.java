
package com.traction.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApplicationRequest {

    @NotNull
    private Integer opportunityId;

    @NotBlank
    @Size(max = 2000)
    private String message;

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
