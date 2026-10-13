package com.traction.backend.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.traction.backend.dto.LoginRequest;
import com.traction.backend.dto.RegisterRequest;
import com.traction.backend.dto.UserResponse;
import com.traction.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserResponse createUser(@Valid @RequestBody RegisterRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public UserResponse loginUser(@Valid @RequestBody LoginRequest request) {
        System.out.println("LOGIN CONTROLLER REACHED");

        UserResponse response = userService.loginUser(request);

        System.out.println("RESPONSE OBJECT IS NULL: " + (response == null));

        return response;
    }

}