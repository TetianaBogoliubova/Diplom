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
public class Rating {

    private UUID id;
    private int ratingOfTeacher;
    private String feedback;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return ratingOfTeacher == rating.ratingOfTeacher && Objects.equals(id, rating.id) && Objects.equals(feedback, rating.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ratingOfTeacher, feedback);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", ratingOfTeacher=" + ratingOfTeacher +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
