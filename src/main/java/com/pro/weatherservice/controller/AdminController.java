package com.pro.weatherservice.controller;

import com.pro.weatherservice.domain.Admin;
import com.pro.weatherservice.dto.AdminDto;
import com.pro.weatherservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody AdminDto adminDto) {
        Admin admin = adminService.save(adminDto);
        return ResponseEntity.ok(admin);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Admin admin = adminService.findById(id);
        return ResponseEntity.ok(admin);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll() {
        List<Admin> adminList = adminService.findAll();
        return ResponseEntity.ok(adminList);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody AdminDto adminDto) {
        Admin admin = adminService.update(id, adminDto);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        adminService.delete(id);
        return ResponseEntity.ok("Admin deleted");
    }
}
