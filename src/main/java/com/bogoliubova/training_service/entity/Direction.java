package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllDirections;
import com.bogoliubova.training_service.entity.enums.AllGradings;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "directions")
public class Direction {

    @Id
    @Column(name = "direction_id", columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID",
//            strategy = "com.bogoliubova.training_service.generator.UuidTimeSequenceGenerator")
//    @Column(name = "direction_id")

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "00000000-0000-0000-0000-000000000000")
    // @JsonFormat(pattern = "00000000-0000-0000-0000-000000000000")
    private UUID directionId;

    @Column(name = "d_title")
    @Enumerated(EnumType.STRING)
    private AllDirections dirTitle;

    @Enumerated(EnumType.STRING)
    private AllGradings grading;


    //public UUID getDirectionId() {
//        return directionId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return Objects.equals(directionId, direction.directionId) && dirTitle == direction.dirTitle && grading == direction.grading;
    }

    @Override
    public int hashCode() {
        return Objects.hash(directionId, dirTitle, grading);
    }

    @Override
    public String toString() {
        return "Direction{" +
                "directionId=" + directionId +
                ", dirTitle=" + dirTitle +
                ", grading=" + grading +
                '}';
    }
}
