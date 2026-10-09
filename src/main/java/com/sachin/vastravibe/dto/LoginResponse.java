package com.sachin.vastravibe.dto;

public class LoginResponse {
	
	 private Long userId;
	    private String firstName;
	    private String email;
	    private String token;
	    
	    
	    public LoginResponse(
	            Long userId,
	            String firstName,
	            String email,
	            String token) {
	        this.userId = userId;
	        this.firstName = firstName;
	        this.email = email;
	        this.token = token;
	    }
	    
	    public Long getUserId() {
	        return userId;
	    }
	    
	    public String getFirstName() {
	        return firstName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getToken() {
	        return token;
	    }
	    
	    

}
