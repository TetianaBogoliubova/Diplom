package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring")//, imports = {Date.class})
public interface TeacherMapper {

    // @Mapping(defaultValue = "ONLINE", target = "typesOfLearning")
    // @Mapping(expression =  "java(new data(System.currentTimeMillis()))", target = "creationDate")
    @Mapping(source = "teachEmail", target = "teachEmail", qualifiedByName = "toUpperCase")
    Teacher toEntity(TeacherDto teacherDto);

    @Named("toUpperCase")
    static String toUpperCase(String teachEmail) {
        return teachEmail.toUpperCase();
    }

}
