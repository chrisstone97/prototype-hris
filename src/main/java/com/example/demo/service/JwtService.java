package com.example.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Map;

@Service
public class JwtService {
    private final KeyPair key = Keys.keyPairFor(SignatureAlgorithm.RS256);

    public String generateJwt(final Map<String, Object> claims) {
        final String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(key.getPrivate())
                .compact();

        return jwt;
    }

    public Map<String, Object> parseJwt(final String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key.getPublic())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
