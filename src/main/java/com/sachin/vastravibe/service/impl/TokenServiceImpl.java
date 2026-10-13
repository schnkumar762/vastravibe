
package com.sachin.vastravibe.service.impl;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.sachin.vastravibe.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }
}
