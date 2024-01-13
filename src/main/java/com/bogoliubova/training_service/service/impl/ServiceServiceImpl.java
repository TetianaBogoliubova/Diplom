package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.repository.ServiceRepository;
import com.bogoliubova.training_service.service.interf.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public com.bogoliubova.training_service.entity.Service getServiceById(String id) {
        return serviceRepository.findServiceByServiceId(UUID.fromString(id));
    }
}
