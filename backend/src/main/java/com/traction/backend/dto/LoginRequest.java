package com.traction.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest{

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=8)
    private String password;

    // @Size(min=8,max=8)
    // private String varification_code;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}