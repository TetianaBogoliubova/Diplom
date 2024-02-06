package com.bogoliubova.training_service.service.interf;
import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.entity.enums.AllDirections;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    Teacher getTeacherById(String id);

    Teacher createNewTeacher(Teacher teacher);

    Teacher create(TeacherDto teacherDto);

    TeacherDto getFLRId(UUID id);

    List<TeacherDto> getTByC(String city);

    List<TeacherDto> getTByR(Integer rating);

    List<TeacherDto> getTByDR(AllDirections dirTitle, Integer ratingOfTeacher);
}