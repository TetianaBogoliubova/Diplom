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

    @GetMapping("/id_book/{book_id}") //
    public Book getBookByBookId(@PathVariable("book_id") String id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/createBook")
    public Book createBook(@RequestBody Book book) {

        return bookService.createNewBook(book);
    }

    @PutMapping(value = "/updateBook/{book_id}")
    public ResponseEntity<Book> updateBookById(@RequestBody Book updateBook, @PathVariable("book_id") String id) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            existingBook.setBookTitle(updateBook.getBookTitle());
            existingBook.setAuthor(updateBook.getAuthor());
            existingBook.setBookPrice(updateBook.getBookPrice());
            existingBook.setDirections(updateBook.getDirections());

            Book updateBookResult = bookService.updateBook(existingBook);

            return new ResponseEntity<>(updateBookResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/part_updateBook/{book_id}")
    public ResponseEntity<Book> patchUpdateBookById(@PathVariable("book_id") String bookId,
                                                    @RequestBody Map<String, Object> updates) {
        if (updates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Book updatedBook = bookService.patchUpdateBook(bookId, updates);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteBook/{book_id}")
    public ResponseEntity<String> deleteBookByBookId(@PathVariable("book_id") String bookId) {
        boolean deleted = bookService.deleteBookById(bookId);
        if (deleted) {
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }
}




//    @PatchMapping("/part_updateBook/{book_id}")
//    public ResponseEntity<Book> patchUpdateBookById(@PathVariable("book_id") String bookId,
//                                                    @RequestParam(value = "bookTitle", required = false) String bookTitle,
//                                                    @RequestParam(value = "author", required = false) String author,
//                                                    @RequestParam(value = "bookPrice", required = false) Double bookPrice) {
//        if (bookTitle == null && author == null && bookPrice == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Book updateBook = bookService.patchUpdateBook(bookId, bookTitle, author, bookPrice);
//        if (updateBook != null) {
//            return new ResponseEntity<>(updateBook, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }