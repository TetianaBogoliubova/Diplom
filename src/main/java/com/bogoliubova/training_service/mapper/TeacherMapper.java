package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.exception.ErrorMassage;
import com.bogoliubova.training_service.exception.TeacherNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(source = "teachEmail", target = "teachEmail", qualifiedByName = "toUpperCase")
    Teacher toEntity(TeacherDto teacherDto);

    @Named("toUpperCase")
    static String toUpperCase(String teachEmail) {
        return teachEmail.toUpperCase();
    }

    @Mapping(source = "teacher.location", target = "location")
   // @Mapping(source = "teacher.directions", target = "directions")
    //TeacherDto toDto(Teacher entity);
    TeacherDto toDto(Teacher teacher);

//    @Mapping(source = "teacher.location", target = "location", qualifiedByName = "getTeacherByCity")
//    @Mapping(source = "teacher.directions", target = "directions", qualifiedByName = "getTeacherByDirection")
//    TeacherDto toDto1(Teacher entity);



    @Mapping(source = "teacher.location", target = "location", qualifiedByName = "getTeacherByCity")
    @Mapping(source = "teacher.directions", target = "directions", qualifiedByName = "getTeacherByDirection")
    default List<TeacherDto> toDtoList(List<Teacher> teachers) {
       return teachers.stream()
               .map(entity -> toDto(entity))
               .collect(Collectors.toList());
    }
    @Named("getTeacherByCity")
    default TeacherDto getTeacherByCity (Teacher teacher) {
        return toDto(teacher);
    }
}
