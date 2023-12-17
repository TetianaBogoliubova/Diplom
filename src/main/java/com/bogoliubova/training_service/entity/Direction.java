package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllDirections;
import com.bogoliubova.training_service.entity.enums.AllGradings;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Direction {
    @Id
    private UUID directionId;
    private AllDirections dirTitle;
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
