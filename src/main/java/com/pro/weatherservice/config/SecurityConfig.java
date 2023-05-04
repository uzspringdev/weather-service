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
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth").permitAll()
                .requestMatchers("/api/v1/register").permitAll()
                .requestMatchers("/api/v1/users/**").hasRole("USER")
                .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer(jwtService));

        return http.build();
    }

    private JWTConfigurer jwtConfigurer(JWTService jwtService) {
        return new JWTConfigurer(jwtService);
    }
}
