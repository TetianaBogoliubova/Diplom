package com.bogoliubova.training_service.repository;


import com.bogoliubova.training_service.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicesRepository extends JpaRepository<Services, UUID> {
    Services findServicesByServiceId(UUID serviceId);
}
