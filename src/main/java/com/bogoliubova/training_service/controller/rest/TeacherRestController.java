package com.bogoliubova.training_service.controller.rest;

import com.bogoliubova.training_service.dto.TeacherDto;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherRestController {

    private final TeacherService teacherService;

    @PostMapping("/createTeacherRest")
    public Teacher createTeacherRest(@RequestBody TeacherDto teacherDto) {
        return teacherService.create(teacherDto);
    }
}
