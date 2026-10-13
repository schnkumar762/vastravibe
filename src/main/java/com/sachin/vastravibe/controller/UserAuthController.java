//package com.sachin.vastravibe.controller;
//
//import java.net.http.HttpHeaders;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sachin.vastravibe.dto.ApiResponse;
//import com.sachin.vastravibe.dto.LoginRequest;
//import com.sachin.vastravibe.dto.LoginResponse;
//import com.sachin.vastravibe.dto.LogoutRequest;
//import com.sachin.vastravibe.dto.LogoutResponse;
//import com.sachin.vastravibe.dto.SessionRequest;
//import com.sachin.vastravibe.dto.SessionResponse;
//import com.sachin.vastravibe.dto.SignupRequest;
//import com.sachin.vastravibe.dto.SignupResponse;
//import com.sachin.vastravibe.service.AuthService;
//
//import lombok.AllArgsConstructor;
//
//
//@RestController
//@RequestMapping("/api/v1/users/auth")
//@AllArgsConstructor
//
//
//public class UserAuthController {
//
//	  private final AuthService authService;
//	  
//	  @PostMapping("/login")
//	  public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request){
//		  LoginResponse response = authService.login(
//	                request.getEmail(),
//	                request.getPassword());
//
//	        return ResponseEntity.ok(new ApiResponse<>("success","Logged in",response));
//		  
//	  }
//	  
//	  @PostMapping("/signup")
//	  public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest request){
//		  SignupResponse response = authService.signup(request);
//		  return ResponseEntity.ok(new ApiResponse<>("success", "Signup successfull",response));
//	  }
//	  
//	  @PostMapping("/logout")
//	  public ResponseEntity<ApiResponse<LogoutResponse>> logout(@RequestBody LogoutRequest request){
//		  LogoutResponse response = authService.logout(request);
//		  return ResponseEntity.ok(new ApiResponse<>("success", "Logged out successfully", response));
//	  }
//	  
////	  @GetMapping("/session")
////	  public ResponseEntity<ApiResponse<SessionResponse>> session(
////	          @RequestParam("id") String id) {
////
////	      SessionResponse response = authService.session(new SessionRequest(id));
////
////	      return ResponseEntity.ok(
////	          new ApiResponse<>("success", "done", response)
////	      );
//	  
//	  @GetMapping("/session")
//	  public SessionResponse session(
//	          @RequestHeader(
//	              value = HttpHeaders.AUTHORIZATION,
//	              required = false
//	          ) String authorization
//	  ) {
//	      if (authorization == null
//	              || !authorization.startsWith("Bearer ")) {
//	          throw new BadCredentialsException("Bearer token required");
//	      }
//
//	      String token = authorization.substring(7).trim();
//
//	      if (token.isBlank()) {
//	          throw new BadCredentialsException("Bearer token required");
//	      }
//
//	      return authService.session(token);
//	  }
//	  }
//	  }
//	  
//	  
//	  
//



package com.sachin.vastravibe.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.vastravibe.dto.ApiResponse;
import com.sachin.vastravibe.dto.LoginRequest;
import com.sachin.vastravibe.dto.LoginResponse;

import com.sachin.vastravibe.dto.LogoutResponse;
import com.sachin.vastravibe.dto.SessionResponse;
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
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(
                request.getEmail(),
                request.getPassword());

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Logged in", response));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(
            @RequestBody SignupRequest request) {

        SignupResponse response = authService.signup(request);

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Signup successful", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<LogoutResponse>> logout(
            @RequestHeader("Authorization") String authorization) {

        String token = authorization.replace("Bearer ", "");

        LogoutResponse response = authService.logout(token);

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Logged out successfully", response));
    }

    @GetMapping("/session")
    public ResponseEntity<ApiResponse<SessionResponse>> session(
            @RequestHeader(
                    value = HttpHeaders.AUTHORIZATION,
                    required = false) String authorization) {

        if (authorization == null
                || !authorization.startsWith("Bearer ")) {
            throw new BadCredentialsException("Bearer token required");
        }

        String token = authorization.substring(7).trim();

        if (token.isBlank()) {
            throw new BadCredentialsException("Bearer token required");
        }

        SessionResponse response = authService.session(token);

        return ResponseEntity.ok(
                new ApiResponse<>("success", "Session verified", response));
    }
}

