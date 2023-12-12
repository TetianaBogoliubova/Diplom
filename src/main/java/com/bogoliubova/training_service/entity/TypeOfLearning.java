package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enams.LearningTypes;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class TypeOfLearning {
    @Getter
    @Setter

    private UUID id;
    private LearningTypes learningTypes;
    private double specialPrice;

    public TypeOfLearning() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfLearning that = (TypeOfLearning) o;
        return specialPrice == that.specialPrice && Objects.equals(id, that.id) && Objects.equals(learningTypes, that.learningTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, learningTypes, specialPrice);
    }

    @Override
    public String toString() {
        return "TypeOfLearning{" +
                "id=" + id +
                ", type='" + learningTypes + '\'' +
                ", specialPrice=" + specialPrice +
                '}';
    }
}
