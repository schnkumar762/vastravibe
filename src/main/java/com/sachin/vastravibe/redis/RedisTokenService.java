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
        redisTemplate.opsForValue()
                .set("user:" + userId, token, Duration.ofHours(1));
    }

    public String getToken(String userId) {
        return redisTemplate.opsForValue()
                .get("user:" + userId);
    }

    public void deleteToken(String userId) {
        redisTemplate.delete("user:" + userId);
    }
}