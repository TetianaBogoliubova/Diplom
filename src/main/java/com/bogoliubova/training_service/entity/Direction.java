package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllDirections;
import com.bogoliubova.training_service.entity.enums.AllGradings;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID directionId;

    @Column(name = "d_title")
    @Enumerated(EnumType.STRING)
    private AllDirections dirTitle;

    @Column(name = "grading")
    @Enumerated(EnumType.STRING)
    private AllGradings grading;

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
