package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.dto.ServicesDto;
import com.bogoliubova.training_service.service.interf.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService servicesService;


    @GetMapping("/id_services/{service_id}")
    public ServicesDto getServicesByServiceId(@PathVariable("service_id") String id) {
        return servicesService.getServicesDtoById(id);
    }

    @PostMapping("/createServices")
    public ServicesDto createServicesDto(@RequestBody ServicesDto servicesDto) {
        return servicesService.createNewServicesDto(servicesDto);
    }
}