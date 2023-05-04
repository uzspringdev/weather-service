package com.pro.weatherservice.service;

import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.dto.CityDto;

import java.io.IOException;
import java.util.List;

public interface CityService {
    City findById(Long id);

    List<City> saveAll() throws IOException;

    List<City> findAll();
}
