package com.traction.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.traction.backend.dto.RegisterRequest;
import com.traction.backend.entity.User;
import com.traction.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User createUser(RegisterRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setLocation(request.getLocation());
        user.setOccupation(request.getOccupation());
        user.setInvestmentMin(request.getInvestmentMin());
        user.setInvestmentMax(request.getInvestmentMax());

        String HashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPasswordHash(HashedPassword);

        return userRepository.save(user);
    }
}