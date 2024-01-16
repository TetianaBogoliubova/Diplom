package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Location;

public interface LocationService {
    Location getLocationById(String id);

    Location createNewLocation(Location location);
}