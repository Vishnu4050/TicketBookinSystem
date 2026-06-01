package com.vishnu.TicketBooking.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // BASE64 ENCODED SECRET KEY
    private static final String SECRET_KEY =
            "VGhpc0lzQVN1cGVyU2VjdXJlSldUU2VjcmV0S2V5MTIzNDU2";

    // GENERATE TOKEN
    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // EXTRACT EMAIL
    public String extractEmail(String token) {

        return extractAllClaims(token).getSubject();
    }

    // VALIDATE TOKEN
    public boolean isTokenValid(String token, String email) {

        final String extractedEmail = extractEmail(token);

        return extractedEmail.equals(email) && !isTokenExpired(token);
    }

    // CHECK EXPIRATION
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // EXTRACT CLAIMS
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // SECRET KEY METHOD
    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}