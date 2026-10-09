package com.sachin.vastravibe.service;

import com.sachin.vastravibe.dto.LoginResponse;
import com.sachin.vastravibe.dto.LogoutRequest;
import com.sachin.vastravibe.dto.LogoutResponse;
import com.sachin.vastravibe.dto.SignupRequest;
import com.sachin.vastravibe.dto.SignupResponse;

public interface AuthService {
	
	  LoginResponse login(String email, String password);
	  SignupResponse signup(SignupRequest request);
	  LogoutResponse logout(LogoutRequest request);
	  

}
