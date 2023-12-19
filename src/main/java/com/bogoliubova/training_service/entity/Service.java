package com.bogoliubova.training_service.entity;

import jakarta.persistence.*;
//import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service")

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private UUID serviceId;

    @JoinColumn(name = "direction_id", referencedColumnName = "directionId")
    private Direction direction;

    @Column(name = "type")
    private String type;

    @Column(name = "s_price")
    private double servicePrice;

    @JoinColumn(name = "bookstore_id", referencedColumnName = "bookstoreId")
    private Bookstore bookstore;

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
                ", directionId=" + direction +
                ", type='" + type + '\'' +
                ", price=" + servicePrice +
                ", bookstoreId=" + bookstore +
                '}';
    }
}
