package com.pro.weatherservice.controller;

import com.pro.weatherservice.domain.User;
import com.pro.weatherservice.dto.UserDto;
import com.pro.weatherservice.model.Login;
import com.pro.weatherservice.security.JWTService;
import com.pro.weatherservice.service.CityService;
import com.pro.weatherservice.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1")
public class HomeController {

    private final JWTService JWTService;
    private final UserService userService;
    private final CityService cityService;

    public HomeController(JWTService JWTService, UserService userService, CityService cityService) {
        this.JWTService = JWTService;
        this.userService = userService;
        this.cityService = cityService;
    }

    @GetMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody Login login) throws IOException {
        String token = JWTService.generateToken(login);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new JWToken(token), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.ok(user);
    }

    static class JWToken {
        private String tokenId;

        public JWToken(String tokenId) {
            this.tokenId = tokenId;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }
    }
}
