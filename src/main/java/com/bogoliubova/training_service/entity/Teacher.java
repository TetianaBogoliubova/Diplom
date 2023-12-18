package com.bogoliubova.training_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.data.annotation.Id;


import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teacher")

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private UUID teacherId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "t_email")
    private String teachEmail;

    @Column(name = "direction_id")
    private Direction direction;

    @Column(name = "location_id")
    private Location location;

    @Column(name = "rating_id")
    private Rating ratingId;

    @Column(name = "type_for_teacher")
    private TypeOfLearning typeOfLearning;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(teachEmail, teacher.teachEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, firstName, lastName, teachEmail);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + teachEmail + '\'' +
                ", directionId=" + direction +
                ", locationId=" + location +
                ", ratingId=" + ratingId +
                ", typeOfLearningId=" + typeOfLearning +
                '}';
    }
}
