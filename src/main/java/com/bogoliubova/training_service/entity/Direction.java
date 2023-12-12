package com.bogoliubova.training_service.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class Direction {

    @Getter
    @Setter

    private UUID id;
    private String title;
    private String grading;

    public Direction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction that = (Direction) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(grading, that.grading);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, grading);
    }

    @Override
    public String toString() {
        return "Direction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", grading='" + grading + '\'' +
                '}';
    }
}
