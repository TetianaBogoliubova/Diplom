package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.exception.TeacherWithThisNameAlreadyExistsException;
import com.bogoliubova.training_service.mapper.TeacherMapper;
import com.bogoliubova.training_service.repository.TeacherRepository;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherById(String id) {
        return teacherRepository.findTeacherByTeacherId(UUID.fromString(id));
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher create(TeacherDto teacherDto) {
        Teacher existingTeacher = teacherRepository.getTeacherByFirstNameAndLastName(teacherDto.getFirstName(), teacherDto.getLastName());
        if (existingTeacher != null) {throw new TeacherWithThisNameAlreadyExistsException();
        }
        //Teacher newTeacher = teacherRepository.save(teacherMapper.toEntity(teacherDto));

        else teacherRepository.save(teacherMapper.toEntity(teacherDto));
        return teacherRepository.getTeacherByFirstNameAndLastName(teacherDto.getFirstName(), teacherDto.getLastName());
    }
}
