package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.dto.LocationDto;
import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.exception.ErrorMassage;
import com.bogoliubova.training_service.exception.LocationNotFoundException;
import com.bogoliubova.training_service.mapper.LocationMapper;
import com.bogoliubova.training_service.repository.LocationRepository;
import com.bogoliubova.training_service.service.interf.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    LocationRepository locationRepository;
    LocationMapper locationMapper;
    @Override
    public LocationDto getLTId(UUID uuid) {
        Location entity = locationRepository.findById(uuid).orElseThrow(() -> new LocationNotFoundException(ErrorMassage.M_LOCATION_NOT_FOUND));
    return locationMapper.toDto(entity);
    }
    @Override
    public Location getLocationById(String id) {
        return locationRepository.findLocationByLocationId(UUID.fromString(id));
    }

    @Override
    public Location createNewLocation(Location location) {
        return locationRepository.save(location);
    }
}

