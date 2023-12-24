package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllServices;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "services")

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private UUID serviceId;

    @OneToMany
    @JoinColumn(name = "direction_id", referencedColumnName = "directionId")
    private List<Direction> direction;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AllServices type;

    @Column(name = "s_price")
    private double servicePrice;

    @OneToMany
    @JoinColumn(name = "book_id", referencedColumnName = "book(bookId)")//???
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Double.compare(servicePrice, service.servicePrice) == 0 && Objects.equals(serviceId, service.serviceId) && type == service.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, type, servicePrice);
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", direction=" + direction +
                ", type=" + type +
                ", servicePrice=" + servicePrice +
                ", books=" + books +
                '}';
    }
}
