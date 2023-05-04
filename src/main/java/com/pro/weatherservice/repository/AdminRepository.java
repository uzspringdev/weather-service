package com.pro.weatherservice.repository;

import com.pro.weatherservice.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUserAccountId(Long userAccountId);
}
