package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.repository.LocationRepository;
import com.bogoliubova.training_service.service.interf.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    LocationRepository locationRepository;

    @Override
    public Location getLocationById(String id) {
        return locationRepository.findLocationByLocationId(UUID.fromString(id));
    }

    @Override
    public Location createNewLocation(Location location) {
        return locationRepository.save(location);
    }
}
