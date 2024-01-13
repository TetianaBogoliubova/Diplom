package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.TypeOfLearning;
import com.bogoliubova.training_service.repository.TypeOfLearningRepository;
import com.bogoliubova.training_service.service.interf.TypeOfLearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TypeOfLearningServiceImpl implements TypeOfLearningService {

    private final TypeOfLearningRepository typeOfLearningRepository;

    @Override
    public TypeOfLearning getTypeOfLearningById(String id) {
        return typeOfLearningRepository.findTypeByTypeId(UUID.fromString(id));
    }
}
