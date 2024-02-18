package com.bogoliubova.training_service.service.implTest;

import com.bogoliubova.training_service.controller.page.BookController;
import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookController bookController;
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
    public void getBookByIdPositiveTest() {

        when(bookRepository.findBookByBookId(UUID.fromString(book.getBookId().toString()))).thenReturn(book);
        Book expectedBook = bookService.getBookById(String.valueOf(UUID.fromString(book.getBookId().toString())));

        assertEquals(expectedBook, book);
        assertNotNull(expectedBook);

    }

    @Test
    public void getBookByIdNegativeTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(null);
        Book expectedBook = bookService.getBookById(String.valueOf(book.getBookId()));
        assertNotEquals(book, expectedBook);
        assertNull(expectedBook);

    }

    @Test
    public void getBookByIdCheckAllParametersTest() {
        Book expectedBook = new Book();

        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(expectedBook);
        Book result = bookService.getBookById(String.valueOf(book.getBookId()));

        assertEquals(expectedBook.getBookId(), result.getBookId());
        assertEquals(expectedBook.getBookTitle(), result.getBookTitle());
        assertEquals(expectedBook.getAuthor(), result.getAuthor());
        assertEquals(expectedBook.getBookPrice(), result.getBookPrice());
    }

    @Test
    public void createNewBookPositiveTest() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book newBook = bookService.createNewBook(book);

        assertNotNull(newBook);
        assertEquals(book, newBook);
    }

    @Test
    public void createNewBookCheckAllParametersTest() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book newBook = bookService.createNewBook(book);

        assertNotNull(newBook.getBookId());
        assertNotNull(newBook.getBookTitle());
        assertNotNull(newBook.getAuthor());
        assertNotNull(newBook.getBookPrice());
        assertNotNull(newBook.getDirections());
    }

    @Test
    public void updateBookPositiveTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book updateBook = new Book();
        updateBook.setBookTitle("New Title1");
        updateBook.setAuthor("New Author1");
        updateBook.setBookPrice(BigDecimal.valueOf(50.00));
        updateBook.setDirections(directions);

        ResponseEntity<Book> expectedBook = bookService.updateBook(updateBook, book.getBookId().toString());

        assertNotNull(expectedBook);
        assertEquals(HttpStatus.OK, expectedBook.getStatusCode());
        assertNotNull(expectedBook.getBody());
    }

    @Test
    public void updateBookInconsistencyOfOldAndNewParametersTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);

        Book updateBook = new Book();

        updateBook.setBookTitle("New Title");
        updateBook.setAuthor("New Author");
        updateBook.setBookPrice(BigDecimal.valueOf(40.00));
        updateBook.setDirections(directions);

        when(bookRepository.save(any(Book.class))).thenReturn(updateBook);

        ResponseEntity<Book> updateResult = bookService.updateBook(updateBook, book.getBookId().toString());
        assertNotNull(updateResult);

        assertNotEquals(book, updateResult.getBody());
        // assertNotEquals(book.getBookTitle(), updateResult.getBody().getBookTitle());
        // assertNotEquals(book.getAuthor(), updateResult.getBody().getAuthor());
        // assertNotEquals(book.getBookPrice(), updateResult.getBody().getBookPrice());
    }

    @Test
    public void updateBookByIdNegativeTest() {
        when(bookRepository.findBookByBookId(any(UUID.class))).thenReturn(null);

        ResponseEntity<Book> updateResult = bookService.updateBook(book, UUID.randomUUID().toString());

        assertEquals(HttpStatus.NOT_FOUND, updateResult.getStatusCode());
        assertNull(updateResult.getBody());
    }

//    @Test
//    public void patchUpdateBookByIdPositiveTest() {
//
//        when(bookRepository.findBookByBookId(any(UUID.class))).thenReturn(book);
//
//        Map<String, Object> updates = new HashMap<>();
//
//        updates.put("bookTitle", "New Title1");
//        updates.put("author", "New Author1");
//        updates.put("bookPriceB", BigDecimal.valueOf(50.00));
//
//
//        ResponseEntity<Book> updateResult = bookService.patchUpdateBookById(book.getBookId().toString(), updates);
//       // when(bookRepository.save(any(Book.class))).thenReturn(updateResult.getBody());
//        when(bookRepository.patchUpdateBook(anyString(), anyMap())).thenReturn(ResponseEntity.ok().build());//body(updateResult.getBody())
//
//        assertNotNull(updateResult);
//        assertEquals(HttpStatus.OK, updateResult.getStatusCode());
//        assertNotNull(updateResult.getBody());
//        assertEquals(book, updateResult.getBody());
//    }


}
//public class BookServiceTest {
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @Test
//    public void testGetBookById() {
//        // Arrange
//        String bookId = "298e7601-e47a-5cd9-f387-125124058224";
//        UUID uuid = UUID.fromString(bookId);
//        Book expectedBook = new Book(); // создайте ожидаемую книгу
//
//        // Mock the repository method
//        when(bookRepository.findBookByBookId(uuid)).thenReturn(expectedBook);
//
//        // Act
//        Book result = bookService.getBookById(bookId);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(expectedBook, result);
//    }
//}