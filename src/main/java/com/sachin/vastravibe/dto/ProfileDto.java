package com.sachin.vastravibe.dto;

import java.time.LocalDate;
import com.sachin.vastravibe.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    private LocalDate dateOfBirth;
    private Gender gender;
}