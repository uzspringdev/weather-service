package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Wind;
import com.pro.weatherservice.dto.WindDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WindMapper {
    WindMapper getInstance = Mappers.getMapper(WindMapper.class);

    Wind toEntity(WindDto windDto);

    WindDto toDto(Wind wind);
}
