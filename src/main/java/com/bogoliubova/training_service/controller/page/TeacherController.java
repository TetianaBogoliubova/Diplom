package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/id_teacher/{teacher_id}")
    public Teacher getTeacherById(@PathVariable("teacher_id") String id) {
        return teacherService.getTeacherById(id);
    }
}
