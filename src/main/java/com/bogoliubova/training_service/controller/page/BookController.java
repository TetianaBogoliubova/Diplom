package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/id_book/{book_id}") //
    public Book getBookById(@PathVariable("book_id") String book_id) {

        return bookService.getBookById(book_id);
    }


//    @GetMapping(value = "/id_book/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE) //
//    public Book getBookById(@PathVariable("book_id") UUID id) {
//        return bookService.getBookById(String.valueOf(id));
//    }

//    @Autowired
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

//        @GetMapping("/id_book/{book_id}")
//    public ResponseEntity<Book> getBookById(@PathVariable("book_id") UUID id) {
//        Book book = bookService.getBookById(UUID.fromString(String.valueOf(id)));
//        return ResponseEntity.ok(book);
//    }

}