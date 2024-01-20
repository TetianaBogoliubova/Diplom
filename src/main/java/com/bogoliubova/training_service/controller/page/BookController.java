package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/id_book/{book_id}") //http://localhost:8080/book/id_book/298e7601-e47a-5cd9-f387-125124058224
    public Book getBookByBookId(@PathVariable("book_id") String id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/createBook") //http://localhost:8080/book/createBook
    public Book createBook(@RequestBody Book book) {
        return bookService.createNewBook(book);
    }


    @PutMapping(value = "/updateBook/{book_id}") //http://localhost:8080/book/updateBook/298e7601-e47a-5cd9-f387-125124058224
    public ResponseEntity<Book> updateBookById(@RequestBody Book updateBook, @PathVariable("book_id") String id) {
        return bookService.updateBook(updateBook, id);
    }

    @PatchMapping("/part_updateBook/{book_id}")
    //http://localhost:8080/book/part_updateBook/226e8867-e33a-2cd3-f362-211620192358
    public ResponseEntity<Book> patchUpdateBookById(@PathVariable("book_id") String bookId,
                                                    @RequestBody Map<String, Object> updates) {
        if (updates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return bookService.patchUpdateBookById(bookId, updates);
    }

    @DeleteMapping("/deleteBook/{book_id}") //http://localhost:8080/book/deleteBook/
    public ResponseEntity<String> deleteBookByBookId(@PathVariable("book_id") String bookId) {
        return bookService.deleteBookById(bookId);
    }
}


