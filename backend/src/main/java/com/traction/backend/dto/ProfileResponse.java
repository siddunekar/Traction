
package com.traction.backend.dto;

public class ProfileResponse {

    private int id;
    private String username;
    private String location;
    private String occupation;
    private Integer investmentMin;
    private Integer investmentMax;
    private String bio;
    private String profilePhotoUrl;
    private String linkedinUrl;
    private String website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getInvestmentMin() {
        return investmentMin;
    }

    public void setInvestmentMin(Integer investmentMin) {
        this.investmentMin = investmentMin;
    }

    public Integer getInvestmentMax() {
        return investmentMax;
    }

    public void setInvestmentMax(Integer investmentMax) {
        this.investmentMax = investmentMax;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
