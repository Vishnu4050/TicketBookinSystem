package com.vishnu.TicketBooking.config;

import com.vishnu.TicketBooking.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.POST, "/users")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST, "/users/login")
                        .permitAll()

                        .requestMatchers("/admin")
                        .hasRole("ADMIN")

                        .requestMatchers("/user")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/events")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/events")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/bookings")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/bookings/my")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/bookings/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/events/paged")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/events/sorted")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/payments/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/events/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/events/**")
                        .hasRole("ADMIN")
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()


                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}