package com.sachin.vastravibe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.vastravibe.dto.LoginRequest;
import com.sachin.vastravibe.dto.LoginResponse;
import com.sachin.vastravibe.dto.SignupRequest;
import com.sachin.vastravibe.dto.SignupResponse;
import com.sachin.vastravibe.service.AuthService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/v1/users/auth")
@AllArgsConstructor


public class UserAuthController {

	  private final AuthService authService;
	  
	  @PostMapping("/login")
	  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		  LoginResponse response = authService.login(
	                request.getEmail(),
	                request.getPassword());

	        return ResponseEntity.ok(response);
		  
	  }
	  
	  @PostMapping("/signup")
	  public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request){
		  SignupResponse response = authService.signup(request);
		  return ResponseEntity.status(201).body(response);
	  }
	  
}
