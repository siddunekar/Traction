
package com.traction.backend.controller;

import com.traction.backend.dto.ProfileResponse;
import com.traction.backend.dto.ProfileUpdateRequest;
import com.traction.backend.service.ProfileService;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/users/me/profile")
    public ProfileResponse getMyProfile(Authentication authentication) {
        Integer userId = (Integer) authentication.getPrincipal();
        return profileService.getProfile(userId);
    }

    @PutMapping("/users/me/profile")
    public ProfileResponse updateMyProfile(
            Authentication authentication,
            @Valid @RequestBody ProfileUpdateRequest request) {

        Integer userId = (Integer) authentication.getPrincipal();
        return profileService.updateProfile(userId, request);
    }

    @GetMapping("/users/{id}/profile")
    public ProfileResponse getUserProfile(@PathVariable Integer id) {
        return profileService.getProfile(id);
    }
}
