package com.vishnu.TicketBooking.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

    public JwtAuthentication(String email) {
        super(email, null, Collections.emptyList());
    }
}