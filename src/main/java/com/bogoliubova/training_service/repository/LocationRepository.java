package com.bogoliubova.training_service.repository;


import com.bogoliubova.training_service.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    Location findLocationByLocationId(UUID id);

}
