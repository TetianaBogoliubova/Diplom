package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.TypeOfLearning;
import com.bogoliubova.training_service.service.interf.TypeOfLearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/typeOfLearning")
public class TypeOfLearningController {

    private final TypeOfLearningService typeOfLearningService;

    @GetMapping("/id_type/{type_id}")
    public TypeOfLearning getTypeOfLearningByTypeId(@PathVariable("type_id") String id) {
        return typeOfLearningService.getTypeOfLearningById(id);
    }
}
