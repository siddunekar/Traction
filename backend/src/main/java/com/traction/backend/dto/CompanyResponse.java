package com.traction.backend.dto;

public class CompanyResponse {

    private Integer id;
    private Integer founderId;
    private String name;
    private String verificationStatus;
    private String category;
    private String website;
    private String field;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFounderId() {
        return founderId;
    }

    public void setFounderId(Integer founderId) {
        this.founderId = founderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}