package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Book getBookById(String id) {
        return bookRepository.findBookByBookId(UUID.fromString(id));
    }

    @Override
    public Book createNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public ResponseEntity<Book> updateBook(Book updateBook, String id) {

        Book existingBook = bookRepository.findBookByBookId(UUID.fromString(id));

        if (existingBook != null) {
            existingBook.setBookTitle(updateBook.getBookTitle());
            existingBook.setAuthor(updateBook.getAuthor());
            existingBook.setBookPrice(updateBook.getBookPrice());
            existingBook.setDirections(updateBook.getDirections());
            existingBook.setServices(updateBook.getServices());

            Book updateBookResult = bookRepository.save(existingBook);

            return new ResponseEntity<>(updateBookResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Book> patchUpdateBookById(String bookId, Book updateBook) {
        Book existingBook = bookRepository.findBookByBookId(UUID.fromString(bookId));

        if (existingBook != null) {
            existingBook.setBookTitle(updateBook.getBookTitle());
            existingBook.setAuthor(updateBook.getAuthor());
            existingBook.setBookPrice(updateBook.getBookPrice());

            Book updateBookResult = bookRepository.save(existingBook);

            return new ResponseEntity<>(updateBookResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    private void applyUpdates(Book book, Map<String, Object> updates) {
//
//        if (updates.containsKey("bookTitle")) {
//            book.setBookTitle((String) updates.get("bookTitle"));
//        }
//        if (updates.containsKey("author")) {
//            book.setAuthor((String) updates.get("author"));
//        }
//        if (updates.containsKey("bookPrice")) {

//
//            //book.setBookPrice((BigDecimal) updates.get("bookPrice"));
//
//            Object priceValue = updates.get("bookPrice");
//            if (priceValue instanceof BigDecimal) {
//                book.setBookPrice((BigDecimal) priceValue);
//            } else if (priceValue instanceof Double) {
//                book.setBookPrice(BigDecimal.valueOf((Double) priceValue));
//            }

//            book.setBookPrice((BigDecimal) updates.get("bookPrice"));

//        }
//    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
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