package com.pro.weatherservice.service.impl;

import com.pro.weatherservice.domain.UserAccount;
import com.pro.weatherservice.dto.UserAccountDto;
import com.pro.weatherservice.mapper.UserAccountMapper;
import com.pro.weatherservice.repository.UserAccountRepository;
import com.pro.weatherservice.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static io.vavr.control.Option.ofOptional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper = UserAccountMapper.getInstance;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount save(UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {

        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount findById(Long id) {
        return ofOptional(userAccountRepository.findById(id))
                .getOrElseThrow(() -> new NoSuchElementException("UserAccount not found"));
    }

    @Override
    public UserAccount findByUsername(String username) {
        return ofOptional(userAccountRepository.findByUsername(username))
                .getOrElseThrow(() -> new NoSuchElementException("UserAccount not found"));
    }

    @Override
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount update(Long id, UserAccountDto userAccountDto) {

        return ofOptional(userAccountRepository.findById(id))
                .peek(userAccount -> userAccountMapper.updateFromDto(userAccountDto, userAccount))
                .peek(userAccountRepository::save)
                .getOrElseThrow(() -> new NoSuchElementException("UserAccount not found"));
    }

    @Override
    public void delete(Long id) {
        ofOptional(userAccountRepository.findById(id))
                .peek(userAccountRepository::delete)
                .getOrElseThrow(() -> new NoSuchElementException("UserAccount not found"));
    }
}
