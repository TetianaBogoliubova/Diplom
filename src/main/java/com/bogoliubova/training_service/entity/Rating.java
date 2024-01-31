package com.bogoliubova.training_service.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@UuidGenerator
    @Column(name = "rating_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID ratingId;

    @Column(name = "rating_for_teacher")
    private int ratingOfTeacher;

    @Column(name = "feedback")
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id",  referencedColumnName = "teacher_id")
    private Teacher teacher;

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
                "ratingId=" + ratingId +
                ", ratingOfTeacher=" + ratingOfTeacher +
                ", feedback='" + feedback + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}