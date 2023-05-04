package com.pro.weatherservice.security;

import com.pro.weatherservice.model.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JWTService {
    @Value("${json.web.token.secret}")
    private String secret;

    @Value("${json.web.token.validation-time}")
    private Long validationTime;

    @Value("${json.web.token.refresh-time}")
    private Long refreshTime;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserDetailsService userDetailsService;

    public JWTService(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(Login login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()
        );
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String roles = authenticate.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Claims claims = Jwts.claims();
        claims.setSubject(login.getUsername());
        claims.put("roles", roles);
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + validationTime);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .compact();
    }

    public String refreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + this.refreshTime);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .compact();
    }

    public Boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            Date now = new Date();
            Date expirationDate = claims.getExpiration();
            return !expirationDate.before(now);
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean shouldRefresh(String token) {

        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            Instant now = Instant.now();
            Instant expirationDate = claims.getExpiration().toInstant();
            Instant refreshTime = expirationDate.minusMillis(this.refreshTime);  //20.04.2023 22:00 4 hours 20.04.2023 18:00
            return now.isAfter(refreshTime);                                    //20.04.2023 16:38
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        if (isValid(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(extractUsername(token));
            return new UsernamePasswordAuthenticationToken(
                    userDetails, "", userDetails.getAuthorities()
            );
        }
        return null;
    }

    @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
