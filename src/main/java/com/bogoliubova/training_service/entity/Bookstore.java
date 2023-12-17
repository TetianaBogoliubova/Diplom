package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllDirections;
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

public class Bookstore {

@Id
    private UUID bookstoreId;
    private Direction directionId;
    private String bookTitle;
    private String author;
    private double bookPrice;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookstore bookstore = (Bookstore) o;
        return bookPrice == bookstore.bookPrice && Objects.equals(bookstoreId, bookstore.bookstoreId) && Objects.equals(bookTitle, bookstore.bookTitle) && Objects.equals(author, bookstore.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookstoreId, bookTitle, author, bookPrice);
    }

    @Override
    public String toString() {
        return "Bookstore{" +
                "id=" + bookstoreId +
                ", directionId=" + directionId +
                ", title='" + bookTitle + '\'' +
                ", author='" + author + '\'' +
                ", price=" + bookPrice +
                '}';
    }
}
