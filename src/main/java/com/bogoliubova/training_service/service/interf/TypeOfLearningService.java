package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.TypeOfLearning;

public interface TypeOfLearningService {
    TypeOfLearning getTypeOfLearningById(String id);

    //TypeOfLearning createNewType(TypeOfLearning type);
}
