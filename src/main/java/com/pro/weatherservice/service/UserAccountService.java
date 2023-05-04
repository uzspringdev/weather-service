package com.pro.weatherservice.service;

import com.pro.weatherservice.domain.UserAccount;
import com.pro.weatherservice.dto.UserAccountDto;

import java.util.List;

public interface UserAccountService {
    UserAccount save(UserAccountDto userAccountDto);

    UserAccount save(UserAccount userAccount);

    UserAccount findById(Long id);

    UserAccount findByUsername(String username);

    List<UserAccount> findAll();

    UserAccount update(Long id, UserAccountDto userAccountDto);

    void delete(Long id);
}
