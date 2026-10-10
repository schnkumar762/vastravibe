package com.sachin.vastravibe.entity;

import java.time.LocalDateTime;

import com.sachin.vastravibe.enums.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
	
	//instance variable
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String phone;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	
	private LocalDateTime createdAt;
	

}
