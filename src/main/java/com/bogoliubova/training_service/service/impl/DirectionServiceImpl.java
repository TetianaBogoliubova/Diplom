package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.repository.DirectionRepository;
import com.bogoliubova.training_service.service.interf.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directoryRepository;

    @Override
    public Direction getDirectionById(String direction_id) {

        return directoryRepository.findDirectionByDirectionId(UUID.fromString(String.valueOf(direction_id)));
        // return directoryRepository.getDirectionByDirectionId(UUID.fromString(String.valueOf(direction_id)));
    }
}
