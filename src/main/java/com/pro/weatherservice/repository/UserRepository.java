package com.pro.weatherservice.repository;

import com.pro.weatherservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserAccountId(Long id);
}
