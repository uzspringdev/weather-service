package com.pro.weatherservice.repository;

import com.pro.weatherservice.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    Optional<WeatherData> findByName(String name);

    List<WeatherData> findAllByNameIn(List<String> list);
}
