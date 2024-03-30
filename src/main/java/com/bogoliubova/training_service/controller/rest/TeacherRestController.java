package com.bogoliubova.training_service.controller.rest;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.dto.TeacherFullNameAndRatingDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.entity.enums.AllDirections;
import com.bogoliubova.training_service.service.interf.TeacherService;
import com.bogoliubova.training_service.validation.annotation.RatingRestChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "TeacherRestController")
public class TeacherRestController {

    private final TeacherService teacherService;

    //создание нововго объекта
    // + встроенная валидация @NotBlank на поля в Dto-классе
    // + exception
    // + ExceptionHandler на MethodArgumentNotValidException.class
    @PostMapping("/createTeacherRest")//http://localhost:8080/teacher/createTeacherRest
    @Operation(summary = "Create a new teacher",
            description = "If necessary fields are filled in, a new teacher is created",
            tags = "Teacher",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Insert json format data according to TeacherDto class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Teacher.class)
                    )
            )
    )
    public Teacher createTeacherRest(@Valid @RequestBody TeacherDto teacherDto) {
        return teacherService.create(teacherDto);
    }

    //поиск имени, фамилии, рейтинга учителя по id
    // + exception
    // + ExceptionHandler на Exception.class(TEACHER_NOT_FOUND) с возвратом url
    //http://localhost:8080/teacher/id_teacherRest/837e8317-e35a-4cd1-f710-387841923887
    @GetMapping("/id_teacherRest/{teacher_id}")
    @Operation(summary = "Return the teacher by id",
            description = "If the teacher id exists in the database, method return TeacherFullNameAndRatingDto class",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Teacher with this id exists"),
                    @ApiResponse(responseCode = "404", description = "Teacher with this id does not exists")
            }
    )
    public TeacherFullNameAndRatingDto getFirstNameAndLastNameAndRatings(@PathVariable("teacher_id") String id) {
        UUID teacherId = UUID.fromString(id);
        return teacherService.getFLRId(String.valueOf(teacherId));
    }

    //поиск учителя по указанному городу
    // + exception
    // + ExceptionHandler на Exception.class(TEACHER_IN_THIS_CITY_NOT_FOUND)
    @GetMapping("/getTeacherCity/{city}")//http://localhost:8080/teacher/getTeacherCity/Vien
    @Operation(summary = "Return the teacher by city",
            description = "If the city exists in the database, method return list of TeacherDto class",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Teachers in this city exist"),
                    @ApiResponse(responseCode = "404", description = "Teachers in this city do not exist")
            }
    )
    public List<TeacherDto> getTeacherByCity(@PathVariable("city") String city) {
        return teacherService.getTByC(city);
    }

    //поиск учителя по рейтингу
    // + SQL-запрос в Repository
    // + специальная валидация на проверку значения рейтинга (1-10)
    // + ExceptionHandler на ConstraintViolationException.class("The number is not within the rating!!!")
    @GetMapping("/getTeacherRating/{rating}")//http://localhost:8080/teacher/getTeacherRating/9
    @Operation(summary = "Return the teacher by rating",
            description = "If the rating is between 1 and 10, method return list of TeacherDto class",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Teachers with this rating exist"),
                    @ApiResponse(responseCode = "404", description = "Teachers with this rating do not exist")
            }
    )
    public List<TeacherDto> getTeacherByRating(@RatingRestChecker @PathVariable("rating") Integer rating) {
        return teacherService.getTByR(rating);
    }

    //поиск учителя по направлению и рейтингу + SQL-запрос в Repository
    //http://localhost:8080/teacher/getTeacherDirAndRating/GERMAN/9
    @GetMapping("/getTeacherDirAndRating/{direction}/{rating}")
    @Operation(summary = "Return the teacher by directions and rating",
            description = "If the rating is between 1 and 10 and direction exists in the database, method return list of TeacherDto class",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Teachers with this rating and with direction exist"),
                    @ApiResponse(responseCode = "404", description = "Teachers with this rating and with direction do not exist")
            }
    )
    public List<TeacherDto> getTeacherByDirectionAndRating(@PathVariable("direction") AllDirections dirTitle, @PathVariable("rating") Integer ratingOfTeacher) {
        return teacherService.getTByDR(dirTitle, ratingOfTeacher);
    }
}