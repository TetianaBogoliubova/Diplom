package com.bogoliubova.training_service.entity;

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

public class Service {

    @Id
    private UUID serviceId;
    private Direction directionId;
    private String type;
    private double servicePrice;
    private Bookstore bookstoreId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return servicePrice == service.servicePrice && Objects.equals(serviceId, service.serviceId) && Objects.equals(type, service.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, type, servicePrice);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + serviceId +
                ", directionId=" + directionId +
                ", type='" + type + '\'' +
                ", price=" + servicePrice +
                ", bookstoreId=" + bookstoreId +
                '}';
    }
}
