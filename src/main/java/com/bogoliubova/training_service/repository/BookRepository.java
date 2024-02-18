package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Book findBookByBookId(UUID bookId);

    @Modifying
    @Query("UPDATE Book b SET b.bookTitle = :bookTitle, b.author = :author, b.bookPrice = :bookPrice WHERE b.bookId = :bookId")
    Integer patchUpdateBook(@Param("bookId") String bookId,
                        @Param("bookTitle") String bookTitle,
                        @Param("author") String author,
                        @Param("bookPrice") BigDecimal bookPrice);

    <V, K> Object patchUpdateBook(String s, Map<K,V> kvMap);
}