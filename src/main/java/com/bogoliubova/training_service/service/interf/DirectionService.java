package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Direction;

import java.util.UUID;

public interface DirectionService {
    Direction getDirectionById(String direction_id);
}
