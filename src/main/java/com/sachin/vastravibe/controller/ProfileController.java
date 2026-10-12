
package com.sachin.vastravibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.vastravibe.dto.ProfileDto;
import com.sachin.vastravibe.service.ProfileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/profiles")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // Create
    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(
            @RequestBody ProfileDto profileDto) {

        ProfileDto savedProfile = profileService.createProfile(profileDto);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(
            @PathVariable("id") Long id,
            @RequestBody ProfileDto profileDto) {

        return ResponseEntity.ok(
                profileService.updateProfile(id, profileDto));
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileById(
            @PathVariable("id") Long id) {

        profileService.deleteProfileById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete all
    @DeleteMapping
    public ResponseEntity<Void> deleteAllProfiles() {

        profileService.deleteAllProfiles();
        return ResponseEntity.noContent().build();
    }
}
