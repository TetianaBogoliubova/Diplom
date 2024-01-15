package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Service;

public interface ServiceService {
    Service getServiceById(String id);

    Service createNewService(Service service);
}
