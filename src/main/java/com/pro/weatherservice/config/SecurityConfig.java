package com.pro.weatherservice.config;

import com.pro.weatherservice.security.JWTConfigurer;
import com.pro.weatherservice.security.JWTSecurityFilter;
import com.pro.weatherservice.security.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private final JWTService jwtService;

    public SecurityConfig(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringRequestMatchers("/api/v1/register")
                .ignoringRequestMatchers("/api/v1/auth")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth").permitAll()
                .requestMatchers("/api/v1/register").permitAll()
                .requestMatchers("/api/v1/users/updateCurrentUser").hasRole("USER")
                .requestMatchers("/api/v1/weather/getSubscription").hasRole("USER")
                .requestMatchers("/api/v1/weather/updateAll").hasRole("ADMIN")
                .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/cities/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer(jwtService));

        return http.build();
    }

    private JWTConfigurer jwtConfigurer(JWTService jwtService) {
        return new JWTConfigurer(jwtService);
    }
}
