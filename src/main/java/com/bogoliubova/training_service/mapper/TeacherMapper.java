package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.dto.TeacherFullNameAndRatingDto;
import com.bogoliubova.training_service.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(source = "teachEmail", target = "teachEmail", qualifiedByName = "toUpperCase")
    Teacher toEntity(TeacherDto teacherDto);

    @Named("toUpperCase")
    static String toUpperCase(String teachEmail) {
        return Optional.ofNullable(teachEmail)
                .map(String::toUpperCase)
                .orElse(null);
    }

    @Mapping(source = "teacher.location", target = "location")
    TeacherDto toDto(Teacher teacher);

    @Mapping(source = "teacher.ratings", target = "ratings")
    TeacherFullNameAndRatingDto toDtoFullName(Teacher teacher);

    @Mapping(source = "teacher.location", target = "location", qualifiedByName = "getTeacherByCity")
    @Mapping(source = "teacher.ratings", target = "ratings", qualifiedByName = "findTeachersRatings")
    @Mapping(source = "teacher.directions", target = "directions", qualifiedByName = "findTeachersByDirectionAndRating")
    List<TeacherDto> toDtoList(List<Teacher> teachers);
//        return teachers.stream()
//                .map(entity -> toDto(entity))
//                .collect(Collectors.toList());


    @Named("getTeacherByCity")
    default TeacherDto getTeacherByCity(Teacher teacher) {
        return toDto(teacher);
    }

    @Named("findTeachersByRatings")
    default TeacherDto findTeachersByRatings(Teacher teacher) {
        return toDto(teacher);
    }

    @Named("findTeachersByDirectionAndRating")
    default TeacherDto findTeacherByDirectionsAndRatings(Teacher teacher) {
        return toDto(teacher);
    }
}