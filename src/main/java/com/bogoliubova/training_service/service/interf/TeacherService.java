package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Teacher;

public interface TeacherService {
    Teacher getTeacherById(String id);

    Teacher createNewTeacher(Teacher teacher);

    Teacher create(TeacherDto teacherDto);
}

