
package com.sachin.vastravibe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.vastravibe.dto.ProfileDto;
import com.sachin.vastravibe.entity.Profile;
import com.sachin.vastravibe.entity.User;
import com.sachin.vastravibe.mapper.ProfileMapper;
import com.sachin.vastravibe.repository.ProfileRepository;
import com.sachin.vastravibe.repository.UserRepository;
import com.sachin.vastravibe.service.ProfileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileDto createProfile(ProfileDto profileDto) {

        User user = userRepository.findById(profileDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = ProfileMapper.mapToProfile(profileDto);
        profile.setUser(user);

        Profile savedProfile = profileRepository.save(profile);

        return ProfileMapper.mapToProfileDto(savedProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(ProfileMapper::mapToProfileDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto getProfileById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return ProfileMapper.mapToProfileDto(profile);
    }

    @Override
    public ProfileDto updateProfile(Long id, ProfileDto profileDto) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setProfileImageUrl(profileDto.getProfileImageUrl());
        profile.setDateOfBirth(profileDto.getDateOfBirth());
        profile.setGender(profileDto.getGender());

        Profile updatedProfile = profileRepository.save(profile);

        return ProfileMapper.mapToProfileDto(updatedProfile);
    }

    @Override
    public void deleteProfileById(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found");
        }

        profileRepository.deleteById(id);
    }

    @Override
    public void deleteAllProfiles() {
        profileRepository.deleteAll();
    }
}
