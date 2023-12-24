package com.bogoliubova.training_service.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
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
@Entity
@Table(name = "ratings")

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private UUID ratingId;

    @Column(name = "rating_for_teacher")
    private int ratingOfTeacher;

    @Column(name = "feedback")
    private String feedback;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return ratingOfTeacher == rating.ratingOfTeacher && Objects.equals(ratingId, rating.ratingId) && Objects.equals(feedback, rating.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId, ratingOfTeacher, feedback);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + ratingId +
                ", ratingOfTeacher=" + ratingOfTeacher +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
