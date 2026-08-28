package com.traction.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traction.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
