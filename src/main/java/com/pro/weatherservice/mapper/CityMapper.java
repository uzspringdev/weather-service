package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.City;
import com.pro.weatherservice.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {
    CityMapper getInstance = Mappers.getMapper(CityMapper.class);

    City toEntity(CityDto cityDto);
}
