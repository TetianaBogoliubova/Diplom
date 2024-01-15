package com.bogoliubova.training_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@UuidGenerator
    @Column(name = "teacher_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID teacherId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "t_email")
    private String teachEmail;

//    @OneToOne
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private Direction direction;

//    @OneToMany(orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private List<Direction> directions;

    @OneToMany
    @JoinColumn(name = "direction_id")
    @JsonIgnore
    private List<Direction> directions;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @JsonIgnore
    private Location location;

    @OneToMany
    @JoinColumn(name = "rating_id")
    @JsonIgnore
    private List<Rating> ratings;

//    @OneToOne
//    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
//    private TypeOfLearning typesOfLearning;

//    @OneToMany(orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
//    @JoinColumn(name = "type_id", referencedColumnName = "type_id")//, insertable = false, updatable = false)
//    private List<TypeOfLearning> typesOfLearning;

    @OneToMany
    @JoinColumn(name = "type_id")
    @JsonIgnore
    private List<TypeOfLearning> typesOfLearning;


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
                ", directionId=" + directions +
                ", locationId=" + location +
                ", ratings=" + ratings +
                ", typeOfLearningId=" + typesOfLearning +
                '}';
    }
}
