package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectionRepository extends JpaRepository<Direction, UUID> {
    Direction findDirectionByDirectionId(UUID directionId);
    //Direction getDirectionByDirectionId(UUID directionId);
}
