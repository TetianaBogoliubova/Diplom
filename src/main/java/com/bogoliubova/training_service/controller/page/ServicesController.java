package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.service.interf.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService servicesService;

//    @GetMapping("/id_services/{services_id}")
//    public Service getServicesByServiceId(@PathVariable("service_id") String id) {
//        return servicesService.getServicesById(id);
//    }

    @PostMapping("/createServices")
    public Services createServices(@RequestBody Services services) {
        return servicesService.createNewServices(services);
    }
}
