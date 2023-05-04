package com.pro.weatherservice.controller;

import com.pro.weatherservice.domain.User;
import com.pro.weatherservice.dto.CityDto;
import com.pro.weatherservice.dto.UserDto;
import com.pro.weatherservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll() {
        List<User> userList = userService.findAll();

        return ResponseEntity.ok(userList);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto) {
        User user = userService.update(id, userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

    @PostMapping(value = "/subscribe")
    public ResponseEntity<?> subscribeCity(@RequestParam Long cityId) {
        User user = userService.subscribeCity(cityId);

        return ResponseEntity.ok(user);
    }
}
