package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Teacher findTeacherByTeacherId(UUID teacherId);
}
