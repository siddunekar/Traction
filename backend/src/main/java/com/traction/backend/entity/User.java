package com.traction.backend.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String phone;

    @Column(name="password_hash")
    private String passwordHash;

    private String location;
    private String occupation;

    @Column(name="investment_min")
    private Integer investmentMin;

    @Column(name="investment_max")
    private Integer investmentMax;

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
