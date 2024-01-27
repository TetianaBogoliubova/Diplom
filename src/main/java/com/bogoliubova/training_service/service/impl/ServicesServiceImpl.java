package com.bogoliubova.training_service.service.impl;


import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.repository.ServicesRepository;
import com.bogoliubova.training_service.service.interf.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    @Override
    public Services getServicesById(String id) {
        return servicesRepository.findServicesByServiceId(UUID.fromString(id));
    }

    @Override
    public Services createNewServices(Services services) {
        return servicesRepository.save(services);
    }
}