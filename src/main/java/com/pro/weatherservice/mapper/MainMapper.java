package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Main;
import com.pro.weatherservice.dto.MainDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MainMapper {

    MainMapper getInstance = Mappers.getMapper(MainMapper.class);

    Main toEntity(MainDto mainDto);

    MainDto toDto(Main main);
}
