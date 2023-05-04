package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Sys;
import com.pro.weatherservice.dto.SysDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysMapper {
    SysMapper getInstance = Mappers.getMapper(SysMapper.class);

    Sys toEntity(SysDto sysDto);

    SysDto toDto(Sys sys);
}
