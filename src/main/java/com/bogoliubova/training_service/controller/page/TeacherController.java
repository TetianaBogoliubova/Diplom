package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.service.interf.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/id_teacher/{teacher_id}")
//http://localhost:8080/teacher/id_teacher/837e8317-e35a-4cd1-f710-387841923887
    public Teacher getTeacherByTeacherId(@Valid @NotNull @PathVariable("teacher_id") String id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping("/createTeacher")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createNewTeacher(teacher);
    }
}
