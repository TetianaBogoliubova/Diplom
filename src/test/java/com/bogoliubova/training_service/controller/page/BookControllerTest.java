package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.interf.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private List<Direction> directions = new ArrayList<>();
    @Mock
    private Services services;
    private final Book book = new Book();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        book.setBookId(UUID.fromString("298e7601-e47a-5cd9-f387-125124058224"));
        book.setBookTitle("The Line of Beauty");
        book.setAuthor("Sherri Miller");
        book.setBookPrice(BigDecimal.valueOf(13.50));
        book.setDirections(directions);
        book.getServices();
    }

    @Test
    public void getBookByBookIdPositiveTest() {
        Book expectedBook = new Book();

        when(bookService.getBookById(String.valueOf(book.getBookId()))).thenReturn(expectedBook);
        Book result = bookController.getBookByBookId(String.valueOf(book.getBookId()));

        assertEquals(expectedBook, result);
        assertNotNull(result);
    }

    @Test
    public void getBookByBookIdNegativeTest() {
        when(bookService.getBookById(String.valueOf(book.getBookId()))).thenReturn(null);
        Book result = bookController.getBookByBookId(String.valueOf(book.getBookId()));

        assertNotEquals(book, result);
        assertNull(result);
    }

    @Test
    public void getBookByBookIdCheckAllParametersTest() {
        Book expectedBook = new Book();

        when(bookService.getBookById(String.valueOf(book.getBookId()))).thenReturn(expectedBook);
        Book result = bookController.getBookByBookId(String.valueOf(book.getBookId()));

        assertEquals(expectedBook.getBookId(), result.getBookId());
        assertEquals(expectedBook.getBookTitle(), result.getBookTitle());
        assertEquals(expectedBook.getAuthor(), result.getAuthor());
        assertEquals(expectedBook.getBookPrice(), result.getBookPrice());
    }

    @Test
    public void createBookPositiveTest() {
        when(bookService.createNewBook(any(Book.class))).thenReturn(book);
        Book newBook = bookController.createBook(book);

        assertNotNull(newBook);
        assertEquals(book, newBook);
    }

    @Test
    public void createBookCheckAllParametersTest() {
        when(bookService.createNewBook(any(Book.class))).thenReturn(book);
        Book newBook = bookController.createBook(book);

        assertNotNull(newBook.getBookId());
        assertNotNull(newBook.getBookTitle());
        assertNotNull(newBook.getAuthor());
        assertNotNull(newBook.getBookPrice());
        assertNotNull(newBook.getDirections());
    }

    @Test
    public void updateBookByIdPositiveTest() {
        when(bookService.getBookById(String.valueOf(book.getBookId()))).thenReturn(book);

        Book updateBook = new Book();

        updateBook.setBookTitle("New Title1");
        updateBook.setAuthor("New Author1");
        updateBook.setBookPrice(BigDecimal.valueOf(50.00));
        updateBook.setDirections(directions);

        when(bookService.updateBook(updateBook, book.getBookId().toString()))
                .thenReturn(new ResponseEntity<>(updateBook, HttpStatus.OK));

        ResponseEntity<Book> updateResult = bookController.updateBookById(updateBook, book.getBookId().toString());

        assertNotNull(updateResult);
        assertEquals(HttpStatus.OK, updateResult.getStatusCode());
        assertNotNull(updateResult.getBody());
        assertEquals(updateBook, updateResult.getBody());
    }

    @Test
    public void updateBookByIdInconsistencyOfOldAndNewParametersTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);

        Book newBook = new Book();

        newBook.setBookTitle("New Title");
        newBook.setAuthor("New Author");
        newBook.setBookPrice(BigDecimal.valueOf(50.00));
        newBook.setDirections(directions);

        //when(bookRepository.save(any(Book.class))).thenReturn(updateBook);

        when(bookService.updateBook(newBook, book.getBookId().toString())).thenReturn(new ResponseEntity<>(newBook, HttpStatus.OK));

        ResponseEntity<Book> updateResult = bookController.updateBookById(newBook, book.getBookId().toString());
        //assertNotNull(updateResult);

        assertNotEquals(book, updateResult.getBody());
        assertNotEquals(book.getBookTitle(), updateResult.getBody().getBookTitle());
        assertNotEquals(book.getAuthor(), updateResult.getBody().getAuthor());
        assertNotEquals(book.getBookPrice(), updateResult.getBody().getBookPrice());
    }

//    @Test
//    public void updateBookByIdNegativeTest() {
//        when(bookRepository.findBookByBookId(any(UUID.class))).thenReturn(null);
//
//        ResponseEntity<Book> updateResult = bookController.updateBookById(book, UUID.randomUUID().toString());
//
//        assertEquals(HttpStatus.NOT_FOUND, updateResult.getStatusCode());
//        assertNull(updateResult.getBody());
//    }

    @Test
    public void patchUpdateBookByIdPositiveTest() {

        when(bookService.getBookById(anyString())).thenReturn(book);

        Map<String, Object> updates = new HashMap<>();

        updates.put("bookTitle", "New Title1");
        updates.put("author", "New Author1");
        updates.put("bookPriceB", BigDecimal.valueOf(50.00));

        when(bookService.patchUpdateBookById(anyString(), anyMap()))
                .thenReturn(new ResponseEntity<>(book, HttpStatus.OK));

        ResponseEntity<Book> updateResult = bookController.patchUpdateBookById(book.getBookId().toString(), updates);

        assertNotNull(updateResult);
        assertEquals(HttpStatus.OK, updateResult.getStatusCode());
        assertNotNull(updateResult.getBody());
    }

    @Test
    public void patchUpdateBookByIdInconsistencyOfOldAndNewParametersTest() {

        when(bookService.getBookById(anyString())).thenReturn(book);

        Map<String, Object> updates = new HashMap<>();

        updates.put("bookTitle", "New Title1");
        updates.put("author", "New Author1");
        updates.put("bookPriceB", BigDecimal.valueOf(50.00));

        when(bookService.patchUpdateBookById(anyString(), anyMap())).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));

        ResponseEntity<Book> updateResult = bookController.patchUpdateBookById(book.getBookId().toString(), updates);

        // assertNotEquals(book, updateResult.getBody());
        assertNotEquals("New Title1", updateResult.getBody().getBookTitle());
        assertNotEquals("New Author1", updateResult.getBody().getAuthor());
        assertNotEquals(BigDecimal.valueOf(50.00), updateResult.getBody().getBookPrice());

    }

    @Test
    public void patchUpdateBookByIdNegativeTest() {

        when(bookRepository.findBookByBookId(any(UUID.class))).thenReturn(null);

        Map<String, Object> updates = new HashMap<>();
        ResponseEntity<Book> updateResult = bookController.patchUpdateBookById(UUID.randomUUID().toString(), updates);

        assertNotNull(updateResult);
        assertEquals(HttpStatus.BAD_REQUEST, updateResult.getStatusCode());
        assertNull(updateResult.getBody());
    }

    @Test
    public void deleteBookByBookIdPositiveTest() {

        Book expectedBook = new Book();

        when(bookService.getBookById(String.valueOf(book.getBookId()))).thenReturn(expectedBook);
        ResponseEntity<String> result = bookController.deleteBookByBookId(String.valueOf(book.getBookId()));

        assertNull(result);
    }

    @Test
    public void deleteBookByBookIdNegativeTest() {
        when(bookService.getBookById(String.valueOf(book.getBookId()))).thenReturn(null);
        Book result = bookController.getBookByBookId(String.valueOf(book.getBookId()));

        assertNotEquals(book, result);
        assertNull(result);
    }
}



