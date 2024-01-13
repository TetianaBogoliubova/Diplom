package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Book findBookByBookId(UUID bookId);
   // Book getBookByBookId(UUID bookId);
}
