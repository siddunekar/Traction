package com.traction.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traction.backend.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    Optional<User> findByEmail(String email);
}
