package com.pro.weatherservice.message;

import com.pro.weatherservice.dto.WeatherDataDto;
import com.pro.weatherservice.service.WeatherService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQConsumer {
    private final WeatherService weatherService;

    @Autowired
    public RabbitMQConsumer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RabbitListener(queues = "weather-data-queue")
    public void receiveCityName(String cityName) {
        try {
            WeatherDataDto weatherDataDto = weatherService.getCurrentWeather(cityName);
            weatherService.save(weatherDataDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

