package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
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
    public Book updateBook(Book existingBook) {
        return bookRepository.save(existingBook);
    }

    @Override
    public Book patchUpdateBook(String bookId, Map<String, Object> updates) {
        UUID uuidBookId = UUID.fromString(bookId);
        Optional<Book> optionalBook = bookRepository.findById(uuidBookId);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            applyUpdates(existingBook, updates);
            return bookRepository.save(existingBook);
        } else {
            return null;
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
    public boolean deleteBookById(String bookId) {
        UUID uuidBookId = UUID.fromString(bookId);
        Optional<Book> book = bookRepository.findById(uuidBookId);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return true;
        } else {
            return false;
        }
    }
}


/////////////////////
//    @Override
//    public Book patchUpdateBook(String bookId, String bookTitle, String author, Double bookPrice) {
//        UUID uuidBookId = UUID.fromString(bookId);
//        int updateRows = bookRepository.patchUpdateBook(uuidBookId.toString(), bookTitle, author, bookPrice);
//        if (updateRows > 0) {
//            return bookRepository.findBookByBookId(uuidBookId);
//        } else {
//            return null;
//        }
//    }




