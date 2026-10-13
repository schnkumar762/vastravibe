package com.sachin.vastravibe.redis;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService {

    private final StringRedisTemplate redisTemplate;

    public RedisTokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String userId, String token) {
    	 Duration ttl = Duration.ofHours(1);
        redisTemplate.opsForValue()
                .set("user:" + userId, token, ttl);
        redisTemplate.opsForValue()
        .set("session:token:" + token, userId, ttl);
}
    

    public String getToken(String userId) {
        return redisTemplate.opsForValue()
                .get("user:" + userId);
    }
    
    public String getUserIdByToken(String token) {
        return redisTemplate.opsForValue()
                .get("session:token:" + token);
    }

    public void deleteToken(String userId) {
    	 String token = getToken(userId);
    	 if (token != null) {
    	        redisTemplate.delete("session:token:" + token);
    	    }
        redisTemplate.delete("user:" + userId);
    }
    
    
}