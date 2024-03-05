package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.ServicesDto;
import com.bogoliubova.training_service.entity.Services;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicesMapper {

    ServicesDto toDto(Services services);

    @Mapping(target = "serviceId", ignore = true)
    @Mapping(target = "directions", ignore = true)
    @Mapping(target = "books", ignore = true)
    Services toEntity(ServicesDto servicesDto);
}
