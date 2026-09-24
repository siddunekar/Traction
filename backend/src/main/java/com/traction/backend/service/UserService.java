package com.traction.backend.service;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.traction.backend.dto.LoginRequest;
import com.traction.backend.dto.RegisterRequest;
import com.traction.backend.dto.UserResponse;
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

    public UserResponse createUser(RegisterRequest request) {

        if(userRepository.existsByUsername(request.getUsername())){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Username already exists");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Email is already in use");
        }

        if(userRepository.existsByPhone(request.getPhone())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "phone is already in use");
        }
        
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

        User savedUser =  userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    public UserResponse loginUser(LoginRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if(userOptional.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Invalid Email or Password");
        }

        User user = userOptional.get();

        boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            user.getPasswordHash()
        );

        if(!passwordMatches){
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Enter Password"
            );
        }

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return response;
    }

    

}