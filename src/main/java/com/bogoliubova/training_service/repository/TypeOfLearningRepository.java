package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.TypeOfLearning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TypeOfLearningRepository extends JpaRepository<TypeOfLearning, UUID> {
    TypeOfLearning findTypeByTypeId(UUID typeId);
}