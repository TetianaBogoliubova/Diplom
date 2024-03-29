package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {
    Book getBookById(String id);

    Book createNewBook(Book book);

    ResponseEntity<Book> updateBook(Book existingBook, String id);

    ResponseEntity<String> deleteBookById(String bookId);

    ResponseEntity<Book> patchUpdateBookById(String bookId, Book updateBook);
}