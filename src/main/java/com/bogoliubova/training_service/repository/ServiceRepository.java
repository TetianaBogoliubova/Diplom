package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
    Service findServiceByServiceId(UUID serviceId);
}