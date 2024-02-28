package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.dto.TeacherFullNameAndRatingDto;
import com.bogoliubova.training_service.entity.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapperImpl implements TeacherMapper {

    private final Teacher teacher = new Teacher();
    private final TeacherDto teacherDto = new TeacherDto();
    private final TeacherFullNameAndRatingDto teacherFNAR = new TeacherFullNameAndRatingDto();

    @Override
    public Teacher toEntity(TeacherDto teacherDto) {
        if (teacherDto == null) {
            return null;
        }
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setTeachEmail(TeacherMapper.toUpperCase(teacherDto.getTeachEmail()));
        teacher.setDirections(teacherDto.getDirections());
        teacher.setLocation(teacherDto.getLocation());
        teacher.setRatings(teacherDto.getRatings());

        return teacher;
    }

    @Override
    public TeacherDto toDto(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setTeachEmail(teacher.getTeachEmail());
        teacherDto.setDirections(teacher.getDirections());
        teacherDto.setLocation(teacher.getLocation());
        teacherDto.setRatings(teacher.getRatings());

        return teacherDto;
    }

    @Override
    public TeacherFullNameAndRatingDto toDtoFullName(Teacher teacher) {

        teacherFNAR.setFirstName(teacher.getFirstName());
        teacherFNAR.setLastName(teacher.getLastName());
        teacherFNAR.setRatings(teacher.getRatings());

        return teacherFNAR;
    }

    @Override
    public List<TeacherDto> toDtoList(List<Teacher> teachers) {
        return teachers.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }
}
