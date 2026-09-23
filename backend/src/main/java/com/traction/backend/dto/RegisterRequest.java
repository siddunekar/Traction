package com.traction.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp="\\d{10}", message="phone number must contain only 10 digits")
    private String phone;

    @NotBlank
    @Size(min=8)
    private String password;

    @NotBlank
    private String location;

    @NotBlank
    private String occupation;

    private Integer investmentMin;
    private Integer investmentMax;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}