package com.BookingServices.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;


@Service
public class JWTService {

    private final String apiKeySid = "SK.0.cGk2aUx9655VWRbEEFJZvhnQkw77VhUL";
    private final String apiKeySecret = "UDEwZWh5bGFKbXl6ZjdoaW52Q1lNZGFVV3pzOVp5SWg=";
    public String generateToken(){
        String token = Jwts.builder()
                .claim("jti", "1")
                .claim("iss", apiKeySid)
                .claim("rest_api", true)
                .setHeaderParam("cty", "stringee-api;v=1")
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
        return token;
    }

    private Key getSignKey(){
        byte[] keyBytes = apiKeySecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
