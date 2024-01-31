package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Location;
import com.bogoliubova.training_service.service.interf.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/id_location/{location_id}")
    public Location getLocationByLocationId(@PathVariable("location_id") String id) {
        return locationService.getLocationById(id);
    }

    @PostMapping("/createLocation")
    public Location createLocation(@RequestBody Location location) {
        return locationService.createNewLocation(location);
    }
}
