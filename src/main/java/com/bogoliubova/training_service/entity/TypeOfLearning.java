package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllLearningTypes;
import jakarta.persistence.*;
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
@Table(name = "types_of_learning")

public class TypeOfLearning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
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


//    To github.com:TetianaBogoliubova/Diplom.git
//        ! [rejected]        master -> master (fetch first)
//        error: failed to push some refs to 'github.com:TetianaBogoliubova/Diplom.git'
//        hint: Updates were rejected because the remote contains work that you do
//        hint: not have locally. This is usually caused by another repository pushing
//        hint: to the same ref. You may want to first integrate the remote changes
//        hint: (e.g., 'git pull ...') before pushing again.
//        hint: See the 'Note about fast-forwards' in 'git push --help' for details.
//
//        user@ThinkPodT430 MINGW64 ~/IdeaProjects/Training_service (master)

