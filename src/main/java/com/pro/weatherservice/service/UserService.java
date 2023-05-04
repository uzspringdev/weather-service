package com.pro.weatherservice.service;

import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.domain.User;
import com.pro.weatherservice.dto.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto userDto);

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    User update(Long id, UserDto userDto);

    void delete(Long id);

    User getCurrentUser();

    User subscribeCity(Long cityId);

}
