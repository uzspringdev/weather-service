package com.pro.weatherservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.dto.CityDto;
import com.pro.weatherservice.mapper.CityMapper;
import com.pro.weatherservice.repository.CityRepository;
import com.pro.weatherservice.service.CityService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static io.vavr.control.Option.ofOptional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ResourceLoader resourceLoader;
    private final CityMapper cityMapper = CityMapper.getInstance;

    public CityServiceImpl(CityRepository cityRepository, ResourceLoader resourceLoader) {
        this.cityRepository = cityRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public City findById(Long cityId) {
        return ofOptional(cityRepository.findById(cityId))
                .getOrElseThrow(()->new NoSuchElementException("City not found"));
    }

    @Override
    public List<City> saveAll() throws IOException {
        return cityRepository.saveAll(getAllCities());
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    private List<City> getAllCities() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = resourceLoader.getResource("classpath:/static/city.list.json");
        List<City> cities = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<City>>() {
        });
        return cityRepository.saveAll(cities);
    }
}
