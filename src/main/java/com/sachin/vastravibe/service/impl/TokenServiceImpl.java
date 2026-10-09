package com.sachin.vastravibe.service.impl;

import org.springframework.stereotype.Service;

import com.sachin.vastravibe.service.TokenService;


@Service
public class TokenServiceImpl implements TokenService {
	
	
	  @Override
	    public String generateToken() {
		  
		  return "dummy$hashed$token$for$a$while";
		  
		  
	  }
	  
	  
	  
	  }
	
	
	

