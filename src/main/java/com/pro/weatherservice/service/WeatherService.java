package com.pro.weatherservice.service;

import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.domain.Weather;
import com.pro.weatherservice.domain.WeatherData;
import com.pro.weatherservice.dto.WeatherDataDto;

import java.io.IOException;
import java.util.List;

public interface WeatherService {

    WeatherData save(WeatherDataDto weatherDataDto);

    WeatherData save(WeatherData weatherData);

    List<WeatherData> saveAll(List<WeatherDataDto> weatherDataDtoList);

    WeatherData findById(Long id);

    WeatherData findByCity(String city);

    List<WeatherData> findAll();

    WeatherData update(Long id, WeatherDataDto weatherDataDto);

    void delete(Long id);

    List<WeatherData> getSubscriptionWeatherData();

    WeatherDataDto getCurrentWeather(String city) throws IOException;

    void updateWeatherData();

}
