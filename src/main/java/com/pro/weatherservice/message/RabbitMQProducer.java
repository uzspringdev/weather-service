package com.pro.weatherservice.message;

import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.service.CityService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;
    private final CityService cityService;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate, CityService cityService) {
        this.rabbitTemplate = rabbitTemplate;
        this.cityService = cityService;
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void sendCityNames() {
        List<City> cityList = cityService.findAll();
        List<String> cityNames = cityList.stream().map(City::getName).collect(Collectors.toList());
        cityNames.forEach(cityName -> {
            rabbitTemplate.convertAndSend("weather-data-queue", cityName);
        });
    }
}
