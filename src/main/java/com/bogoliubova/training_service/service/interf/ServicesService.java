package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.dto.ServicesDto;

public interface ServicesService {
    ServicesDto getServicesDtoById(String id);

    ServicesDto createNewServicesDto(ServicesDto servicesDto);
}
