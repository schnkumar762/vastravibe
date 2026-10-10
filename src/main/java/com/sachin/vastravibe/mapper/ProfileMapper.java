
package com.sachin.vastravibe.mapper;

import com.sachin.vastravibe.dto.ProfileDto;
import com.sachin.vastravibe.entity.Profile;

public class ProfileMapper {

    public static ProfileDto mapToProfileDto(Profile profile) {
        ProfileDto dto = new ProfileDto();

        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setProfileImageUrl(profile.getProfileImageUrl());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setGender(profile.getGender());

        return dto;
    }

    public static Profile mapToProfile(ProfileDto dto) {
        Profile profile = new Profile();

        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setProfileImageUrl(dto.getProfileImageUrl());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setGender(dto.getGender());

        return profile;
    }
}
