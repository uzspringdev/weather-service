package com.pro.weatherservice.service.impl;

import com.pro.weatherservice.domain.Admin;
import com.pro.weatherservice.dto.AdminDto;
import com.pro.weatherservice.mapper.AdminMapper;
import com.pro.weatherservice.repository.AdminRepository;
import com.pro.weatherservice.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.vavr.control.Option.ofOptional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;
    private final AdminMapper adminMapper = AdminMapper.getInstance;


    public AdminServiceImpl(AdminRepository adminRepository, BCryptPasswordEncoder encoder) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = adminMapper.toEntity(adminDto);
        admin.getUserAccount().setPassword(encoder.encode(admin.getUserAccount().getPassword()));
        return adminRepository.save(admin);

    }

    @Override
    public Admin save(Admin admin) {
        admin.getUserAccount().setPassword(encoder.encode(admin.getUserAccount().getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(Long id) {
        return ofOptional(adminRepository.findById(id))
                .getOrElseThrow(() -> new NoSuchElementException("Admin not found"));
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional
    public Admin update(Long id, AdminDto adminDto) {
        return ofOptional(adminRepository.findById(id))
                .map(admin -> adminMapper.updateAdminFromDto(adminDto, admin))
                .peek(admin -> admin.getUserAccount().setPassword(encoder.encode(admin.getUserAccount().getPassword())))
                .peek(adminRepository::save)
                .getOrElseThrow(() -> new NoSuchElementException("Admin not Found"));
    }

    @Override
    public void delete(Long id) {
        ofOptional(adminRepository.findById(id))
                .peek(adminRepository::delete)
                .getOrElseThrow(() -> new NoSuchElementException("Admin not found"));
    }
}
