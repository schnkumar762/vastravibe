package com.sachin.vastravibe.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.vastravibe.entity.Profile;


public interface ProfileRepository extends JpaRepository<Profile, Long> {

	
}
