package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Coord;
import com.pro.weatherservice.dto.CoordDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoordMapper {
    CoordMapper getInstance = Mappers.getMapper(CoordMapper.class);

    Coord toEntity(CoordDto coordDto);

    CoordDto toDto(Coord coord);
}
