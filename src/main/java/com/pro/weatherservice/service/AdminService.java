package com.pro.weatherservice.service;

import com.pro.weatherservice.domain.Admin;
import com.pro.weatherservice.dto.AdminDto;

import java.util.List;

public interface AdminService {
    Admin save(AdminDto adminDto);

    Admin save(Admin admin);

    Admin findById(Long id);

    List<Admin> findAll();

    Admin update(Long id, AdminDto adminDto);

    void delete(Long id);
}
