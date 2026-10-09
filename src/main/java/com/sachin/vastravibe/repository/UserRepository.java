package com.sachin.vastravibe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.vastravibe.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	 Optional<User> findByEmail(String email);
}
