package com.traction.backend.service;
import org.springframework.beans.factory.annotation.Value;
import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(Integer userId, String email) {

        return Jwts.builder()
            .subject(String.valueOf(userId))
            .claim("email", email)
            .signWith(getSigningKey())
            .compact();
    }
}