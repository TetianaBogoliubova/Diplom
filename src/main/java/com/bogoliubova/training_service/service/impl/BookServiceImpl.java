package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book getBookById(String id) {
        return bookRepository.findBookByBookId(UUID.fromString(id));
    }

    @Override
    public Book createNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public ResponseEntity<Book> updateBook(Book updateBook, String id) {

        Book existingBook = bookRepository.findBookByBookId(UUID.fromString(id));

        if (existingBook != null) {
            existingBook.setBookTitle(updateBook.getBookTitle());
            existingBook.setAuthor(updateBook.getAuthor());
            existingBook.setBookPrice(updateBook.getBookPrice());
            existingBook.setDirections(updateBook.getDirections());

            Book updateBookResult = bookRepository.save(existingBook);

            return new ResponseEntity<>(updateBookResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Book> patchUpdateBookById(String bookId, Map<String, Object> updates) {
        UUID uuidBookId = UUID.fromString(bookId);
        Optional<Book> optionalBook = bookRepository.findById(uuidBookId);//.orElseThrow(() -> BookEx("now f" + updateBook()));

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            applyUpdates(existingBook, updates);

            Book updatedBook = bookRepository.save(existingBook);

            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private void applyUpdates(Book book, Map<String, Object> updates) {

        if (updates.containsKey("bookTitle")) {
            book.setBookTitle((String) updates.get("bookTitle"));
        }
        if (updates.containsKey("author")) {
            book.setAuthor((String) updates.get("author"));
        }
        if (updates.containsKey("bookPrice")) {
            book.setBookPrice((Double) updates.get("bookPrice"));
        }
    }

    @Override
    public ResponseEntity<String> deleteBookById(String bookId) {
        UUID uuidBookId = UUID.fromString(bookId);
        Optional<Book> book = bookRepository.findById(uuidBookId);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }
}