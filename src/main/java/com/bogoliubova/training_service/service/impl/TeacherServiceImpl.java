package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.repository.TeacherRepository;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacherById(String id) {
        return teacherRepository.findTeacherByTeacherId(UUID.fromString(id));
    }
}
