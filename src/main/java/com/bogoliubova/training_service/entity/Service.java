package com.bogoliubova.training_service.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class Service {
    @Getter
    @Setter

    private UUID id;
    private Direction directionId;
    private String type;
    private int price;
    private Bookstore bookstoreId;

    public Service() {
    }

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
