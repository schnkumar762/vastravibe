package com.sachin.vastravibe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.vastravibe.dto.ApiResponse;
import com.sachin.vastravibe.dto.LoginRequest;
import com.sachin.vastravibe.dto.LoginResponse;
import com.sachin.vastravibe.dto.LogoutRequest;
import com.sachin.vastravibe.dto.LogoutResponse;
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
	  public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request){
		  LoginResponse response = authService.login(
	                request.getEmail(),
	                request.getPassword());

	        return ResponseEntity.ok(new ApiResponse<>("success","Logged in",response));
		  
	  }
	  
	  @PostMapping("/signup")
	  public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest request){
		  SignupResponse response = authService.signup(request);
		  return ResponseEntity.ok(new ApiResponse<>("success", "Signup successfull",response));
	  }
	  
	  @PostMapping("/logout")
	  public ResponseEntity<ApiResponse<LogoutResponse>> logout(@RequestBody LogoutRequest request){
		  LogoutResponse response = authService.logout(request);
		  return ResponseEntity.ok(new ApiResponse<>("success", "Logged out successfully", response));
	  }
	  
}
