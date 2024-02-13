package com.bogoliubova.training_service.controller.rest;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.dto.TeacherFullNameAndRatingDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.entity.enums.AllDirections;
import com.bogoliubova.training_service.service.interf.TeacherService;
import com.bogoliubova.training_service.validation.annotation.RatingRestChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Validated
public class TeacherRestController {

    private final TeacherService teacherService;

    //создание нововго объекта
    // + встроенная валидация @NotBlank на поля в Dto-классе
    // + exception
    // + ExceptionHandler на MethodArgumentNotValidException.class

    @PostMapping("/createTeacherRest")//http://localhost:8080/teacher/createTeacherRest
    public Teacher createTeacherRest(@Valid @RequestBody TeacherDto teacherDto) {

        return teacherService.create(teacherDto);
    }


    //поиск имени, фамилии, рейтинга учителя по id
    // + exception
    // + ExceptionHandler на Exception.class(TEACHER_NOT_FOUND) с возвратом url
    @GetMapping("/id_teacherRest/{teacher_id}")
//http://localhost:8080/teacher/id_teacherRest/837e8317-e35a-4cd1-f710-387841923887
    public TeacherFullNameAndRatingDto getFirstNameAndLastNameAndRatings(@PathVariable("teacher_id") String id) {
        UUID teacherId = UUID.fromString(id);
        return teacherService.getFLRId(String.valueOf(teacherId));
    }

    //поиск учителя по указанному городу
    // + exception
    // + ExceptionHandler на Exception.class(TEACHER_IN_THIS_CITY_NOT_FOUND)
    @GetMapping("/getTeacherCity/{city}")//http://localhost:8080/teacher/getTeacherCity/Vien
    public List<TeacherDto> getTeacherByCity(@PathVariable("city") String city) {

        return teacherService.getTByC(city);
    }

    //поиск учителя по рейтингу
    // + SQL-запрос в Repository
    // + специальная валидация на проверку значения рейтинга (1-10)
    // + ExceptionHandler на ConstraintViolationException.class("The number is not within the rating!!!")
    @GetMapping("/getTeacherRating/{rating}")//http://localhost:8080/teacher/getTeacherRating/9
    public List<TeacherDto> getTeacherByRating(@RatingRestChecker @PathVariable("rating") Integer rating) {
        return teacherService.getTByR(rating);
    }

    //поиск учителя по направлению и рейтингу + SQL-запрос в Repository
    @GetMapping("/getTeacherDirAndRating/{direction}/{rating}")
    //http://localhost:8080/teacher/getTeacherDirAndRating/GERMAN/9
    public List<TeacherDto> getTeacherByDirectionAndRating(@PathVariable("direction") AllDirections dirTitle, @PathVariable("rating") Integer ratingOfTeacher) {
        return teacherService.getTByDR(dirTitle, ratingOfTeacher);
    }
}