package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Rain;
import com.pro.weatherservice.dto.RainDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RainMapper {
    RainMapper getInstance = Mappers.getMapper(RainMapper.class);

    Rain toEntity(RainDto rainDto);

    Rain toDto(Rain rain);
}
