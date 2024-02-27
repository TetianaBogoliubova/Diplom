package com.bogoliubova.training_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "book_id", columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID bookId;

    @Column(name = "b_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "b_price")
    private BigDecimal bookPrice;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Direction> directions;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    @JsonIgnore
    private Services services;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookPrice, book.bookPrice) &&
                Objects.equals(bookId, book.bookId) &&
                Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(author, book.author);
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
                ", directions=" + directions +
                ", services=" + services +
                '}';
    }
}
