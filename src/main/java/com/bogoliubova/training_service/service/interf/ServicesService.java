package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Services;

public interface ServicesService {
    Services getServicesById(String id);

    Services createNewServices(Services services);
}
