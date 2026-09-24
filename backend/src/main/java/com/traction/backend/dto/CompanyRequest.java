package com.traction.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotBlank
    private String field;

    @NotBlank
    private String description;

    @NotBlank
    private String website;

    @NotBlank
    private String category;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}