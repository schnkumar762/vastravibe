
package com.sachin.vastravibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.security.authentication.BadCredentialsException;
import com.sachin.vastravibe.redis.RedisTokenService;

import com.sachin.vastravibe.dto.ProfileDto;
import com.sachin.vastravibe.service.ProfileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/profiles")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final RedisTokenService redisTokenService;

    // Create profile
    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(
            @RequestBody ProfileDto profileDto) {

        ProfileDto savedProfile = profileService.createProfile(profileDto);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    // Read all profiles
    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    // Read logged-in user's profile
    @GetMapping("/me")
    public ResponseEntity<ProfileDto> getMyProfile(
            @RequestHeader("Authorization") String authorization) {

        String token = authorization.replaceFirst("(?i)^Bearer\\s+", "");

        String userId = redisTokenService.getUserIdByToken(token);

        if (userId == null || userId.isBlank()) {
            throw new BadCredentialsException("Invalid or expired session");
        }

        ProfileDto profile = profileService.getProfileByUserId(Long.valueOf(userId));

        return ResponseEntity.ok(profile);
    }

 // Update logged-in user's profile
    @PutMapping("/me")
    public ResponseEntity<ProfileDto> updateMyProfile(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ProfileDto profileDto) {

        String token = authorization.replaceFirst("(?i)^Bearer\\s+", "");
        String userId = redisTokenService.getUserIdByToken(token);

        if (userId == null || userId.isBlank()) {
            throw new BadCredentialsException("Invalid or expired session");
        }

        ProfileDto currentProfile =
                profileService.getProfileByUserId(Long.valueOf(userId));

        ProfileDto updatedProfile =
                profileService.updateProfile(currentProfile.getId(), profileDto);

        return ResponseEntity.ok(updatedProfile);
    }

    // Delete logged-in user's profile
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyProfile(
            @RequestHeader("Authorization") String authorization) {

        String token = authorization.replaceFirst("(?i)^Bearer\\s+", "");
        String userId = redisTokenService.getUserIdByToken(token);

        if (userId == null || userId.isBlank()) {
            throw new BadCredentialsException("Invalid or expired session");
        }

        ProfileDto currentProfile =
                profileService.getProfileByUserId(Long.valueOf(userId));

        profileService.deleteProfileById(currentProfile.getId());

        return ResponseEntity.noContent().build();
    }

    // Delete all profiles
    @DeleteMapping
    public ResponseEntity<Void> deleteAllProfiles() {
        profileService.deleteAllProfiles();
        return ResponseEntity.noContent().build();
    }
}
