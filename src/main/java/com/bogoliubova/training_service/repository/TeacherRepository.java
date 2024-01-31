package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Teacher;
import com.bogoliubova.training_service.entity.enums.AllDirections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    Teacher findTeacherByTeacherId(UUID teacherId);

    Teacher getTeacherByFirstNameAndLastName(String firstName, String lastName);

    List<Teacher> findTeachersByLocation_City(String city);

    @Query("SELECT t FROM Teacher t " +
            "JOIN t.ratings r " +
            "WHERE r.ratingOfTeacher = :ratingOfTeacher")
    List<Teacher> findTeachersByRatings(@Param("ratingOfTeacher") Integer ratingOfTeacher);


    @Query("SELECT t FROM Teacher t " +
            "JOIN t.ratings r " +
            "JOIN  t.directions d " +
            "WHERE d.dirTitle = :direction " +
            "AND r.ratingOfTeacher = :ratingOfTeacher")
    List<Teacher> findTeachersByDirectionAndRating(@Param("direction") AllDirections dirTitle, @Param("ratingOfTeacher") Integer ratingOfTeacher);
}




