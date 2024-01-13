package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Service;
import com.bogoliubova.training_service.service.interf.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/id_service/{service_id}")
    public Service getServiceByServiceId(@PathVariable("service_id") String id) {

        return serviceService.getServiceById(id);
    }
}
