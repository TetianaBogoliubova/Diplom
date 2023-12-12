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

public class Service {

    private UUID id;
    private Direction directionId;
    private String type;
    private int price;
    private Bookstore bookstoreId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return price == service.price && Objects.equals(id, service.id) && Objects.equals(type, service.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", directionId=" + directionId +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", bookstoreId=" + bookstoreId +
                '}';
    }
}
