package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")

public class BookController {

    private final BookService bookService;

    @GetMapping("/b/{book_id}") //
    public Book getBookById(@PathVariable("book_id") String id) {
        return bookService.getBookById(id);
    }
}
