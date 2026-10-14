
package com.traction.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.traction.backend.dto.ProfileResponse;
import com.traction.backend.dto.ProfileUpdateRequest;
import com.traction.backend.entity.User;
import com.traction.backend.repository.UserRepository;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileResponse getProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        return mapToResponse(user);
    }

    public ProfileResponse updateProfile(
            Integer userId, ProfileUpdateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        user.setUsername(request.getUsername());
        user.setLocation(request.getLocation());
        user.setOccupation(request.getOccupation());
        user.setInvestmentMin(request.getInvestmentMin());
        user.setInvestmentMax(request.getInvestmentMax());
        user.setBio(request.getBio());
        user.setProfilePhotoUrl(request.getProfilePhotoUrl());
        user.setLinkedinUrl(request.getLinkedinUrl());
        user.setWebsite(request.getWebsite());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    private ProfileResponse mapToResponse(User user) {
        ProfileResponse response = new ProfileResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setLocation(user.getLocation());
        response.setOccupation(user.getOccupation());
        response.setInvestmentMin(user.getInvestmentMin());
        response.setInvestmentMax(user.getInvestmentMax());
        response.setBio(user.getBio());
        response.setProfilePhotoUrl(user.getProfilePhotoUrl());
        response.setLinkedinUrl(user.getLinkedinUrl());
        response.setWebsite(user.getWebsite());

        return response;
    }
}
