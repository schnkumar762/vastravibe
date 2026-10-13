package com.sachin.vastravibe.service.impl;

import java.time.LocalDateTime;


import java.util.HashMap;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sachin.vastravibe.dto.LoginResponse;
import com.sachin.vastravibe.dto.LogoutResponse;

import com.sachin.vastravibe.dto.SessionResponse;
import com.sachin.vastravibe.dto.SignupRequest;
import com.sachin.vastravibe.dto.SignupResponse;
import com.sachin.vastravibe.entity.User;
import com.sachin.vastravibe.enums.UserStatus;
import com.sachin.vastravibe.redis.RedisTokenService;
import com.sachin.vastravibe.repository.UserRepository;
import com.sachin.vastravibe.service.AuthService;
import com.sachin.vastravibe.service.TokenService;


import java.util.Map;
import com.sachin.vastravibe.dto.ProfileDto;
import com.sachin.vastravibe.service.ProfileService;

@Service
public class AuthServiceImpl implements AuthService {

	
	    private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final TokenService tokenService;
	    private final RedisTokenService redisTokenService;
	    
	    private final ProfileService profileService;
	    
	    public AuthServiceImpl(
	            UserRepository userRepository,
	            PasswordEncoder passwordEncoder,
	            TokenService tokenService,
	            RedisTokenService redisTokenService,
	            ProfileService profileService) {

	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.tokenService = tokenService;
	        this.redisTokenService = redisTokenService;
	        this.profileService = profileService;
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
		public LogoutResponse logout(String token) {
		    String userId = redisTokenService.getUserIdByToken(token);

		    if (userId == null || userId.isBlank()) {
		        throw new BadCredentialsException("Invalid or expired session");
		    }

		    redisTokenService.deleteToken(userId);

		    return new LogoutResponse("Logout successful");
		}
		
		@Override
		public SessionResponse session(String token) {

		    System.out.println("========== SESSION DEBUG START ==========");

		    System.out.println("Token received: "
		            + (token != null && !token.isBlank()));

		    if (token == null || token.isBlank()) {
		        System.out.println("FAILED: Token missing");
		        throw new BadCredentialsException("Invalid or expired session");
		    }

		    String userId = redisTokenService.getUserIdByToken(token);

		    System.out.println("User ID found in Redis: " + userId);

		    if (userId == null || userId.isBlank()) {
		        System.out.println("FAILED: Reverse mapping not found");
		        throw new BadCredentialsException("Invalid or expired session");
		    }

		    String savedToken = redisTokenService.getToken(userId);

		    System.out.println("User token exists: " + (savedToken != null));

		    boolean tokenMatches = token.equals(savedToken);
		    System.out.println("Token matches: " + tokenMatches);

		    if (!tokenMatches) {
		        System.out.println("FAILED: Token mismatch");
		        throw new BadCredentialsException("Invalid or expired session");
		    }

		    Map<String, Object> profile = new HashMap<>();

		    ProfileDto profileDto =
		            profileService.getProfileByUserId(Long.valueOf(userId));

		    System.out.println("Profile found: " + (profileDto != null));

		    if (profileDto != null) {
		        profile.put("profileId", profileDto.getId());
		    }

		    System.out.println("SESSION VALID");
		    System.out.println("========== SESSION DEBUG END ==========");

		    return new SessionResponse(
		            userId,
		            true,
		            profile,
		            new HashMap<>(),
		            new HashMap<>(),
		            new HashMap<>(),
		            new HashMap<>(),
		            new HashMap<>()
		    );
		}
		
		   

		   
}

