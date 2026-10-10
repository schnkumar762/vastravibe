package com.sachin.vastravibe.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sachin.vastravibe.dto.LoginResponse;
import com.sachin.vastravibe.dto.LogoutRequest;
import com.sachin.vastravibe.dto.LogoutResponse;
import com.sachin.vastravibe.dto.SessionRequest;
import com.sachin.vastravibe.dto.SessionResponse;
import com.sachin.vastravibe.dto.SignupRequest;
import com.sachin.vastravibe.dto.SignupResponse;
import com.sachin.vastravibe.entity.User;
import com.sachin.vastravibe.enums.UserStatus;
import com.sachin.vastravibe.redis.RedisTokenService;
import com.sachin.vastravibe.repository.UserRepository;
import com.sachin.vastravibe.service.AuthService;
import com.sachin.vastravibe.service.TokenService;

@Service
public class AuthServiceImpl implements AuthService {

	
	    private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final TokenService tokenService;
	    private final RedisTokenService redisTokenService;
	    
	    
	    public AuthServiceImpl(
	            UserRepository userRepository,
	            PasswordEncoder passwordEncoder,
	            TokenService tokenService,
	            RedisTokenService redisTokenService) {

	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.tokenService = tokenService;
	        this.redisTokenService = redisTokenService;
	    }
	    
	    @Override
	    
	    public LoginResponse login(String email, String password) {

	    	 User user = userRepository.findByEmail(email)
	                 .orElseThrow(() ->
	                         new BadCredentialsException("Invalid email or password"));

	         if (!passwordEncoder.matches(password, user.getPassword())) {
	             throw new BadCredentialsException("Invalid email or password");
	         }

	        

	         String token = tokenService.generateToken();

	         redisTokenService.saveToken(
	                 user.getId().toString(),
	                 token
	         );

	         return new LoginResponse(
	                 user.getId(),
	                 user.getEmail(),
	                 token
	                 );
	         
	    
	    }
	    
	    @Override
		public SignupResponse signup(SignupRequest request) {
			// TODO Auto-generated method stub
		  if (userRepository.findByEmail(request.getEmail()).isPresent()) {
	          throw new IllegalArgumentException("Email already registered");
	      }

	      User user = new User();
	      user.setCreatedAt(LocalDateTime.now());
	      user.setEmail(request.getEmail());
	      user.setPhone(request.getPhone());
	      user.setPassword(passwordEncoder.encode(request.getPassword()));
	      user.setStatus(UserStatus.ACTIVE);
	      

	      User savedUser = userRepository.save(user);

	      return new SignupResponse(
	              savedUser.getId(),
	              savedUser.getEmail(),
	              savedUser.getPhone()
	              
	      );
	  }

		@Override
		public LogoutResponse logout(LogoutRequest request) {
			redisTokenService.deleteToken(request.getUserId());
			
			   return new LogoutResponse("Logout successful");
		}
@Override
		public SessionResponse session(SessionRequest request) {
			  String userId = request.getId();

			    String token = redisTokenService.getToken(userId);

			    boolean active = token != null && !token.isBlank();

			    return new SessionResponse(userId, active,new HashMap<>(), 
			            new HashMap<>(),
			            new HashMap<>(),
			            new HashMap<>(),
			            new HashMap<>(),
			            new HashMap<>());
		}

	
	
}
