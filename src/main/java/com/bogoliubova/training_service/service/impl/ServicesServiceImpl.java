package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.dto.ServicesDto;
import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.mapper.ServicesMapper;
import com.bogoliubova.training_service.repository.ServicesRepository;
import com.bogoliubova.training_service.service.interf.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;
    private final ServicesMapper servicesMapper;

    @Override
    public ServicesDto getServicesDtoById(String id) {
        return servicesMapper.toDto(servicesRepository.getReferenceById(UUID.fromString(id)));
    }

    @Override
    public ServicesDto createNewServicesDto(ServicesDto servicesDto) {
        Services services = servicesMapper.toEntity(servicesDto);
        Services savedServices = servicesRepository.save(services);
        return servicesMapper.toDto(savedServices);
    }
}
