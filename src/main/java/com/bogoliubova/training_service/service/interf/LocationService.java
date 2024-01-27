package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.dto.LocationDto;
import com.bogoliubova.training_service.entity.Location;
import org.springframework.stereotype.Component;

import java.util.UUID;


public interface LocationService {
    Location getLocationById(String id);

    Location createNewLocation(Location location);

    LocationDto getLTId(UUID uuid);
}
