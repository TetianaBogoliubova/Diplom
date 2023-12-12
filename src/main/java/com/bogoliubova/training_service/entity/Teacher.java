package com.bogoliubova.training_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Direction directionId;
    private Location locationId;
    private Rating ratingId;
    private TypeOfLearning typeOfLearningId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(email, teacher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", directionId=" + directionId +
                ", locationId=" + locationId +
                ", ratingId=" + ratingId +
                ", typeOfLearningId=" + typeOfLearningId +
                '}';
    }
}
