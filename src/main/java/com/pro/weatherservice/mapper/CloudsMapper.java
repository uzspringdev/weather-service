package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Clouds;
import com.pro.weatherservice.dto.CloudsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CloudsMapper {

    CloudsMapper getInstance = Mappers.getMapper(CloudsMapper.class);

    Clouds toEntity(CloudsDto cloudsDto);

    CloudsDto toDto(Clouds clouds);
}
