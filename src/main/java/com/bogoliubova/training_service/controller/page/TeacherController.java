package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Customer;
import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.service.interf.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "Teacher")
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    //http://localhost:8080/teacher/id_teacher/837e8317-e35a-4cd1-f710-387841923887
    @GetMapping("/id_teacher/{teacher_id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Return the teacher by id",
            description = "If the teacher id exists in the database, all information on this teacher is displayed",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Teacher with this id exists"),
                    @ApiResponse(responseCode = "404", description = "Teacher with this id does not exists")
            }
    )
    public Teacher getTeacherByTeacherId(@PathVariable("teacher_id") String id) {
        return teacherService.getTeacherById(id);
    }

    //http://localhost:8080/teacher/createTeacher
    @PostMapping("/createTeacher")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create a new teacher",
            description = "If necessary fields are filled in, a new teacher is created",
            tags = "Teacher",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Insert jason format data according to Teacher Entity class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)
                    )
            )
    )
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createNewTeacher(teacher);
    }
}