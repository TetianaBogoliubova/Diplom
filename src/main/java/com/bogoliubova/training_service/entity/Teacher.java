package com.bogoliubova.training_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")

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

    @OneToMany
    @JoinColumn(name = "direction_id", referencedColumnName = "directionId")
    private List<Direction> direction;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "locationId")
    private Location location;

    @OneToOne
    @JoinColumn(name = "rating_id", referencedColumnName = "ratingId")
    private Rating ratingId;

    @OneToMany
    @JoinColumn(name = "type_id", referencedColumnName = "typeId")
    private List<TypeOfLearning> typeOfLearning;


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
