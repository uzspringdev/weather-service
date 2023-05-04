package com.pro.weatherservice.repository;

import com.pro.weatherservice.domain.UserAccount;
import com.pro.weatherservice.dto.UserAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);
}
