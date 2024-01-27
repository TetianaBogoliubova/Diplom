package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.dto.LocationDto;
import com.bogoliubova.training_service.service.interf.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private  final LocationService locationService;

    @GetMapping("/id_location/{location_id}")
    public LocationDto getLocationAndTeacher(@PathVariable("location_id") String id) {
        return locationService.getLTId(UUID.fromString(id));
    }
}
