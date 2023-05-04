package com.pro.weatherservice.service.impl;

import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.domain.User;
import com.pro.weatherservice.domain.UserAccount;
import com.pro.weatherservice.dto.UserDto;
import com.pro.weatherservice.mapper.UserMapper;
import com.pro.weatherservice.repository.UserRepository;
import com.pro.weatherservice.service.CityService;
import com.pro.weatherservice.service.UserAccountService;
import com.pro.weatherservice.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static io.vavr.control.Option.ofOptional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserAccountService userAccountService;
    private final CityService cityService;

    private final UserMapper userMapper = UserMapper.getInstance;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder, UserAccountService userAccountService, CityService cityService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userAccountService = userAccountService;
        this.cityService = cityService;
    }

    @Override
    public User save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.getUserAccount().setPassword(encoder.encode(user.getUserAccount().getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        user.getUserAccount().setPassword(encoder.encode(user.getUserAccount().getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return ofOptional(userRepository.findById(id))
                .getOrElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, UserDto userDto) {
        return ofOptional(userRepository.findById(id))
                .map(user -> userMapper.updateUserFromDto(userDto, user))
                .peek(user -> user.getUserAccount().setPassword(encoder.encode(user.getUserAccount().getPassword())))
                .peek(userRepository::save)
                .getOrElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public void delete(Long id) {
        ofOptional(userRepository.findById(id))
                .getOrElseThrow(() -> new NoSuchElementException("User deleted"));
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
            Long userAccountId = userAccount.getId();
            return userRepository.findByUserAccountId(userAccountId);
        }
        return null;
    }

    @Override
    public User subscribeCity(Long cityId) {
        City city = cityService.findById(cityId);
        User currentUser = getCurrentUser();
        Set<City> cities = currentUser.getCities();
        cities.add(city);
        currentUser.setCities(cities);
        return userRepository.save(currentUser);
    }

}
