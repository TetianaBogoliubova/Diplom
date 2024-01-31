package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.ServicesDto;
import com.bogoliubova.training_service.entity.Services;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    ServicesDto toDto(Services referenceById);

    Services toEntity(ServicesDto servicesDto);
}
