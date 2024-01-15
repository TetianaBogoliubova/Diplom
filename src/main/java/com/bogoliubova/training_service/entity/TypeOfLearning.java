package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllLearningTypes;
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
@Table(name = "types_of_learning")
public class TypeOfLearning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@UuidGenerator
    @Column(name = "type_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID typeId;

    @Column(name = "learning_types")
    @Enumerated(EnumType.STRING)
    private AllLearningTypes learningTypes;

    @Column(name = "special_price")
    private double specialPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfLearning that = (TypeOfLearning) o;
        return Double.compare(specialPrice, that.specialPrice) == 0 && Objects.equals(typeId, that.typeId) && learningTypes == that.learningTypes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, learningTypes, specialPrice);
    }

    @Override
    public String toString() {
        return "TypeOfLearning{" +
                "typeId=" + typeId +
                ", learningTypes=" + learningTypes +
                ", specialPrice=" + specialPrice +
                '}';
    }
}


