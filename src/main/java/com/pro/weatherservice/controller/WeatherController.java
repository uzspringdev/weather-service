package com.pro.weatherservice.controller;

import com.pro.weatherservice.domain.WeatherData;
import com.pro.weatherservice.dto.WeatherDataDto;
import com.pro.weatherservice.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/current/{city}")
    public ResponseEntity<?> getCurrentWeather(@PathVariable(name = "city") String city) throws IOException {
        //WeatherData tashkent = weatherService.save(weatherService.getCurrentWeather(city));
        return ResponseEntity.ok("tashkent");
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll() {
        List<WeatherData> weatherDataList = weatherService.findAll();
        return ResponseEntity.ok(weatherDataList);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        WeatherData weatherData = weatherService.findById(id);
        return ResponseEntity.ok(weatherData);
    }

    @GetMapping(value = "/findByCity/{city}")
    public ResponseEntity<?> getByCity(@PathVariable(name = "city") String city) {
        WeatherData weatherData = weatherService.findByCity(city);
        return ResponseEntity.ok(weatherData);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody WeatherDataDto weatherDataDto) {
        WeatherData weatherData = weatherService.update(id, weatherDataDto);
        return ResponseEntity.ok(weatherData);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        weatherService.delete(id);
        return ResponseEntity.ok("WeatherData deleted");
    }

}
