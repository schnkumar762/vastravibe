package com.sachin.vastravibe.service;

import java.util.List;

import com.sachin.vastravibe.dto.ProfileDto;

public interface ProfileService {
	
	 // Create
    ProfileDto createProfile(ProfileDto profileDto);

    // Read
    List<ProfileDto> getAllProfiles();

    ProfileDto getProfileById(Long id);

    // Update
    ProfileDto updateProfile(Long id, ProfileDto profileDto);

    // Delete
    void deleteProfileById(Long id);

    // Delete all
    void deleteAllProfiles();

}
