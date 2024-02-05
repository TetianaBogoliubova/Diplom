package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.dto.TeacherFullNameAndRatingDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.entity.enums.AllDirections;
import com.bogoliubova.training_service.exception.ErrorMassage;
import com.bogoliubova.training_service.exception.TeacherNotFoundException;
import com.bogoliubova.training_service.exception.TeacherWithThisNameAlreadyExistsException;
import com.bogoliubova.training_service.mapper.TeacherMapper;
import com.bogoliubova.training_service.repository.LocationRepository;
import com.bogoliubova.training_service.repository.TeacherRepository;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final LocationRepository locationRepository;
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
    public TeacherFullNameAndRatingDto getFLRId(String id) {
        Optional<TeacherFullNameAndRatingDto> entity = teacherRepository.findById(UUID.fromString(id))
                .map(t -> teacherMapper.toDtoFullName(t));
        return entity.orElseThrow(() -> new TeacherNotFoundException(ErrorMassage.M_TEACHER_NOT_FOUND));
    }

    @Override
    public List<TeacherDto> getTByC(String city) {
        List<Teacher> teachers = teacherRepository.findTeachersByLocation_City(city);
        if (!teachers.isEmpty()) {
            return teacherMapper.toDtoList(teachers);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TeacherDto> getTByR(Integer ratingOfTeacher) {
        List<Teacher> teachers = teacherRepository.findTeachersByRatings(ratingOfTeacher);
        return teacherMapper.toDtoList(teachers);
    }

    @Override
    public List<TeacherDto> getTByDR(AllDirections dirTitle, Integer ratingOfTeacher) {
        List<Teacher> teachers = teacherRepository.findTeachersByDirectionAndRating(dirTitle, ratingOfTeacher);
        return teacherMapper.toDtoList(teachers);
    }
}


//    @Override
//    public Teacher create(TeacherDto teacherDto) {
//        // Создаем учителя

   // Teacher createNewTeacher(Teacher teacher)


//        Teacher teacher = new Teacher();
//        teacher.setFirstName(teacherDto.getFirstName());
//        teacher.setLastName(teacherDto.getLastName());
//        teacher.setTeachEmail(teacherDto.getTeachEmail());
//
//        // Сохраняем учителя
//        teacherRepository.save(teacher);
//
//        // Получаем местоположение из DTO
//        LocationDto locationDto = teacherDto.getLocationDto();
//
//        // Создаем местоположение и связываем его с учителем
//        Location location = new Location();
//        location.setCountry(locationDto.getCountry());
//        location.setCity(locationDto.getCity());
//        location.setPostalCode(locationDto.getPostalCode());
//        location.setTeacher(teacher);
//
//        // Сохраняем местоположение
//        locationRepository.save(location);
//
//        return teacher;
//    }
