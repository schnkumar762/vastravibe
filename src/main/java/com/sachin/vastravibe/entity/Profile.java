package com.sachin.vastravibe.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sachin.vastravibe.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "profiles")

public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	  @JoinColumn(name = "user_id", nullable = false, unique = true)
	  private User user;
	  @Column(name="first_name")
	  private String firstName;
	  @Column(name = "last_name")
	private String lastName;
	  @Column(name="profile_image_url")
	private String profileImageUrl;
	  @Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	  @Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	

}
