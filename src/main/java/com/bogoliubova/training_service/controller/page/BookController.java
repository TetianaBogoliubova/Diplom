package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/id_book/{book_id}") //
    public Book getBookByBookId(@PathVariable("book_id") String id) {
        return bookService.getBookById(id);
    }

//    @PostMapping("/createBook")
//    public Book createBook(@RequestBody Book book) {
//        return bookService.createNewBook(book);
//    }
}