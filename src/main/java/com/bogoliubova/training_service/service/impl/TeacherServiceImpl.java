package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Rating;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.exception.ErrorMassage;
import com.bogoliubova.training_service.exception.TeacherNotFoundException;
import com.bogoliubova.training_service.exception.TeacherWithThisNameAlreadyExistsException;
import com.bogoliubova.training_service.mapper.TeacherMapper;
import com.bogoliubova.training_service.repository.TeacherRepository;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {


    // @Autowired
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
        if (existingTeacher != null) {
            throw new TeacherWithThisNameAlreadyExistsException();
        } else teacherRepository.save(teacherMapper.toEntity(teacherDto));
        return teacherRepository.getTeacherByFirstNameAndLastName(teacherDto.getFirstName(), teacherDto.getLastName());
    }

    @Override
    public TeacherDto getFLRId(UUID id) {
        Teacher entity = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(ErrorMassage.M_TEACHER_NOT_FOUND));
        return teacherMapper.toDto(entity);
    }

    @Override
    public List<TeacherDto> getTByC(String city) {
        List<Teacher> teachers = teacherRepository.findTeachersByLocation_City(city);
        if (!teachers.isEmpty()){
            return teacherMapper.toDtoList(teachers);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TeacherDto> getTByR(Integer ratingOfTeacher) {
        List<Teacher> teachers = teacherRepository.findTeachersByRatings(ratingOfTeacher);
        return teacherMapper.toDtoList(teachers);
    }









//    @Override
//    public List<TeacherDto> getTByDR(String direction, Integer rating) {
//        List<Teacher> teachers = teacherRepository.findTeachersByDirectionAndRating(direction, rating);
//        return teacherMapper.toDtoList(teachers);
//    }










//    @Override
//    public List<TeacherDto> getTByDR(String direction, Integer rating) {
//        List<Teacher> teachers = teacherRepository.findTeacherByDirectionsAndRatings(direction, rating);
//        if (!teachers.isEmpty()){
//            return teacherMapper.toDtoList(teachers);
//        }
//        return Collections.emptyList();
//    }

}
