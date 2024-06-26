package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.dto.TeacherFullNameAndRatingDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.entity.enums.AllDirections;
import com.bogoliubova.training_service.exception.ErrorMassage;
import com.bogoliubova.training_service.exception.TeacherInThisCityNotFound;
import com.bogoliubova.training_service.exception.TeacherNotFoundException;
import com.bogoliubova.training_service.exception.ThatTeacherAlreadyExists;
import com.bogoliubova.training_service.mapper.TeacherMapper;
import com.bogoliubova.training_service.repository.TeacherRepository;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Teacher getTeacherById(String id) {
        return teacherRepository.findTeacherByTeacherId(UUID.fromString(id));
    }

    @Override
    @Transactional
    public Teacher createNewTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public Teacher create(TeacherDto teacherDto) {
        Teacher existingTeacher = teacherRepository.getTeacherByFirstNameAndLastName(teacherDto.getFirstName(), teacherDto.getLastName());
        if (existingTeacher != null) {
            throw new ThatTeacherAlreadyExists(ErrorMassage.M_THAT_TEACHER_ALREADY_EXISTS);
        } else teacherRepository.save(teacherMapper.toEntity(teacherDto));
        return teacherRepository.getTeacherByFirstNameAndLastName(teacherDto.getFirstName(), teacherDto.getLastName());
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public TeacherFullNameAndRatingDto getFLRId(String id) {
        Optional<TeacherFullNameAndRatingDto> entity = teacherRepository.findById(UUID.fromString(id))
                .map(teacherMapper::toDtoFullName);
        return entity.orElseThrow(() -> new TeacherNotFoundException(ErrorMassage.M_TEACHER_NOT_FOUND));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<TeacherDto> getTByC(String city) {
        List<Teacher> teachers = teacherRepository.findTeachersByLocation_City(city);
        if (!teachers.isEmpty()) {
            return teacherMapper.toDtoList(teachers);
        } else throw new TeacherInThisCityNotFound(ErrorMassage.M_TEACHER_IN_THIS_CITY_NOT_FOUND);

    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<TeacherDto> getTByR(Integer ratingOfTeacher) {
        List<Teacher> teachers = teacherRepository.findTeachersByRatings(ratingOfTeacher);
        return teacherMapper.toDtoList(teachers);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<TeacherDto> getTByDR(AllDirections dirTitle, Integer ratingOfTeacher) {
        List<Teacher> teachers = teacherRepository.findTeachersByDirectionAndRating(dirTitle, ratingOfTeacher);
        return teacherMapper.toDtoList(teachers);
    }
}