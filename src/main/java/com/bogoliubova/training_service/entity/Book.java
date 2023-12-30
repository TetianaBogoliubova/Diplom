package com.bogoliubova.training_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "b_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "b_price")
    private double bookPrice;

//    @OneToOne
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private Direction direction;

//    @OneToMany//(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private List<Direction> directions;

    @OneToMany
    @JoinColumn(name = "direction_id")
    private List<Direction> directions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookPrice == book.bookPrice && Objects.equals(bookId, book.bookId) && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookTitle, author, bookPrice);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", author='" + author + '\'' +
                ", bookPrice=" + bookPrice +
              //  ", directions=" + directions +
                '}';
    }
}
