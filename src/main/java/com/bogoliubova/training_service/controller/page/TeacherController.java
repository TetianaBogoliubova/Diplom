package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.service.interf.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/id_teacher/{teacher_id}")
    public Teacher getTeacherByTeacherId(@PathVariable("teacher_id") String id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping("/createTeacher")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createNewTeacher(teacher);
    }
}
