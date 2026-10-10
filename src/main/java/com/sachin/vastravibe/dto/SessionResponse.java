package com.sachin.vastravibe.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SessionResponse {
	
	 private String userId;
     private boolean active;
     
     private Map<String, Object> profile;
     private Map<String, Object> order;
     private Map<String, Object> cart;
     private Map<String, Object> wallet;
     private Map<String, Object> wishlist;
     private Map<String,Object> address;
     

}
