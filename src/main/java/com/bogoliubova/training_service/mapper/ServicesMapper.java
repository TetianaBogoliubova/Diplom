package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.ServicesDto;
import com.bogoliubova.training_service.entity.Services;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicesMapper {

    @Mapping(target = "servicePrice", ignore = true)
    @Mapping(target = "type", ignore = true)
    ServicesDto toDto(Services services);

    Services toEntity(ServicesDto servicesDto);
}
