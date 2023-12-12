package com.bogoliubova.training_service.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class Bookstore {

    @Getter
    @Setter

    private UUID id;
    private Direction directionId;
    private String title;
    private String author;
    private double price;

    public Bookstore() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookstore bookstore = (Bookstore) o;
        return price == bookstore.price && Objects.equals(id, bookstore.id) && Objects.equals(title, bookstore.title) && Objects.equals(author, bookstore.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price);
    }

    @Override
    public String toString() {
        return "Bookstore{" +
                "id=" + id +
                ", directionId=" + directionId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
