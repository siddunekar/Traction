package com.traction.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/users", "/login").permitAll()

                        .requestMatchers(HttpMethod.GET, "/companies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/companies/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/companies/{id}").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/me/profile").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/users/me/profile").authenticated()
                        .requestMatchers(HttpMethod.GET, "/users/{id}/profile").permitAll()
                        .requestMatchers(HttpMethod.GET, "/opportunities", "/opportunities/company/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/opportunities").authenticated()
                        .requestMatchers("/applications/**").authenticated()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }
}