package com.pro.weatherservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.domain.WeatherData;
import com.pro.weatherservice.dto.WeatherDataDto;
import com.pro.weatherservice.mapper.WeatherDataMapper;
import com.pro.weatherservice.message.RabbitMQProducer;
import com.pro.weatherservice.repository.WeatherDataRepository;
import com.pro.weatherservice.service.CityService;
import com.pro.weatherservice.service.WeatherService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static io.vavr.control.Option.ofOptional;


@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String API_KEY;

    @Value("${weather.api.url}")
    private String API_URL;

    private final HttpClient httpClient = HttpClientBuilder.create().build();
    private final WeatherDataMapper weatherDataMapper = WeatherDataMapper.getInstance;
    private final WeatherDataRepository weatherDataRepository;
    private final CityService cityService;
    private final RabbitMQProducer rabbitMQProducer;

    public WeatherServiceImpl(WeatherDataRepository weatherDataRepository, CityService cityService, RabbitMQProducer rabbitMQProducer) {
        this.weatherDataRepository = weatherDataRepository;
        this.cityService = cityService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public WeatherData save(WeatherDataDto weatherDataDto) {
        WeatherData weatherData = weatherDataMapper.toEntity(weatherDataDto);

        return weatherDataRepository.save(weatherData);
    }

    @Override
    public WeatherData save(WeatherData weatherData) {
        return weatherDataRepository.save(weatherData);
    }

    @Override
    public List<WeatherData> saveAll(List<WeatherDataDto> weatherDataDtoList) {
        List<WeatherData> weatherDataList = weatherDataDtoList.stream().map(weatherDataMapper::toEntity).collect(Collectors.toList());
        return weatherDataRepository.saveAll(weatherDataList);
    }

    @Override
    public WeatherData findById(Long id) {
        return ofOptional(weatherDataRepository.findById(id))
                .getOrElseThrow(() -> new NoSuchElementException("WeatherData not found"));
    }

    @Override
    public WeatherData findByCity(String city) {
        return ofOptional(weatherDataRepository.findByName(city))
                .getOrElseThrow(() -> new NoSuchElementException("WeatherData not found"));
    }

    @Override
    public List<WeatherData> findAll() {
        return weatherDataRepository.findAll();
    }

    @Override
    public WeatherData update(Long id, WeatherDataDto weatherDataDto) {
        return ofOptional(weatherDataRepository.findById(id))
                .peek(weatherData -> weatherDataMapper.toEntity(weatherDataDto))
                .peek(weatherDataRepository::save)
                .getOrElseThrow(() -> new NoSuchElementException("WeatherData not found"));
    }

    @Override
    public void delete(Long id) {
        ofOptional(weatherDataRepository.findById(id))
                .peek(weatherDataRepository::delete)
                .getOrElseThrow(() -> new NoSuchElementException("WeatherData not found"));
    }

    @Override
    //@Scheduled(fixedRate = 30 * 60 * 1000)
    public void updateCurrentWeather() {
        List<City> cityList = cityService.findAll();
        List<WeatherDataDto> weatherDataDtoList = cityList.stream().map(City::getName).map(name -> {
            try {
                return getCurrentWeather(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());


    }

    public WeatherDataDto getCurrentWeather(String city) throws IOException {
        String encodedCityName = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = String.format(API_URL, encodedCityName, API_KEY);
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed to get weather data: " + response.getStatusLine().getStatusCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;

        StringBuilder sb = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(sb.toString(), WeatherDataDto.class);

    }


}
