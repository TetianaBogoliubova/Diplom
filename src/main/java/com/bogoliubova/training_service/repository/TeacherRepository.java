package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Teacher findTeacherByTeacherId(UUID teacherId);

    Teacher getTeacherByFirstNameAndLastName(String firstName, String lastName);

    // Teacher getTeacherByLocation_City(String city);
    List<Teacher> findTeachersByLocation_City(String city);
}
