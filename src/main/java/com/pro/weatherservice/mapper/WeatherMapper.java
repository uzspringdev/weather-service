package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Weather;
import com.pro.weatherservice.domain.WeatherData;
import com.pro.weatherservice.dto.WeatherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeatherMapper {
    WeatherMapper getInstance = Mappers.getMapper(WeatherMapper.class);

    Weather toEntity(WeatherDto weatherDto);

    WeatherDto toDto(Weather weather);
}
