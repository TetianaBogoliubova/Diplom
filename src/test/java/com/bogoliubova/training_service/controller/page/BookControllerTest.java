package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.service.interf.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
    @Mock
    private List<Direction> directions = new ArrayList<>();

    @Mock
    private Services services = new Services();

    private final Book book = new Book();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        book.setBookId(UUID.fromString("298e7601-e47a-5cd9-f387-125124058224"));
        book.setBookTitle("The Line of Beauty");
        book.setAuthor("Sherri Miller");
        book.setBookPrice(BigDecimal.valueOf(13.50));
        book.setDirections(directions);
        book.setServices(services);
    }

    @Test
    public void getBookByBookIdPositiveTest() {

        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);
        Book expectedBook = bookController.getBookByBookId(book.getBookId().toString());

        assertEquals(expectedBook, book);
        assertNotNull(expectedBook);

        verify(bookService).getBookById(book.getBookId().toString());
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookByBookIdNegativeTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(null);
        Book expectedBook = bookController.getBookByBookId(book.getBookId().toString());

        assertNotEquals(book, expectedBook);
        assertNull(expectedBook);

        verify(bookService).getBookById(book.getBookId().toString());
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookByBookIdCheckAllParametersTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);
        Book expectedBook = bookController.getBookByBookId(book.getBookId().toString());

        assertEquals(expectedBook.getBookId(), book.getBookId());
        assertEquals(expectedBook.getBookTitle(), book.getBookTitle());
        assertEquals(expectedBook.getAuthor(), book.getAuthor());
        assertEquals(expectedBook.getBookPrice(), book.getBookPrice());

        verify(bookService).getBookById(book.getBookId().toString());
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void createBookPositiveTest() {
        when(bookService.createNewBook(any(Book.class))).thenReturn(book);
        Book expectedBook = bookController.createBook(book);

        assertNotNull(expectedBook);
        assertEquals(book, expectedBook);

        verify(bookService).createNewBook(book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void createBookCheckAllParametersTest() {
        when(bookService.createNewBook(any(Book.class))).thenReturn(book);
        Book expectedBook = bookController.createBook(book);

        assertNotNull(expectedBook.getBookId());
        assertNotNull(expectedBook.getBookTitle());
        assertNotNull(expectedBook.getAuthor());
        assertNotNull(expectedBook.getBookPrice());
        assertNotNull(expectedBook.getDirections());
        assertEquals(UUID.fromString("298e7601-e47a-5cd9-f387-125124058224"), expectedBook.getBookId());
        assertEquals("The Line of Beauty", expectedBook.getBookTitle());
        assertEquals("Sherri Miller", expectedBook.getAuthor());
        assertEquals(BigDecimal.valueOf(13.50), expectedBook.getBookPrice());

        verify(bookService).createNewBook(book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void updateBookByIdPositiveTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);

        book.setBookTitle("New Title1");
        book.setAuthor("New Author1");
        book.setBookPrice(BigDecimal.valueOf(50.00));
        book.setDirections(directions);
        book.setServices(services);

        when(bookService.updateBook(book, book.getBookId().toString()))
                .thenReturn(new ResponseEntity<>(book, HttpStatus.OK));

        ResponseEntity<Book> expectedBook = bookController.updateBookById(book, book.getBookId().toString());

        assertNotNull(expectedBook);
        assertEquals(HttpStatus.OK, expectedBook.getStatusCode());
        assertNotNull(expectedBook.getBody());
        assertEquals(book, expectedBook.getBody());

        verify(bookService).updateBook(book, book.getBookId().toString());
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void updateBookByIdCheckNewParametersTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);

        book.setBookTitle("New Title");
        book.setAuthor("New Author");
        book.setBookPrice(BigDecimal.valueOf(50.00));
        book.setDirections(directions);
        book.setServices(services);

        when(bookService.updateBook(book, book.getBookId().toString())).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));

        ResponseEntity<Book> expectedBook = bookController.updateBookById(book, book.getBookId().toString());

        assertEquals(book, expectedBook.getBody());
        assertEquals(book.getBookTitle(), Objects.requireNonNull(expectedBook.getBody()).getBookTitle());
        assertEquals(book.getAuthor(), expectedBook.getBody().getAuthor());
        assertEquals(book.getBookPrice(), expectedBook.getBody().getBookPrice());

        verify(bookService).updateBook(book, book.getBookId().toString());
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void updateBookByIdNegativeTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(null);

        ResponseEntity<Book> expectedBook = bookController.updateBookById(book, book.getBookId().toString());

        assertNull(expectedBook);

        verify(bookService).updateBook(book, book.getBookId().toString());
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void patchUpdateBookByIdPositiveTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);

        when(bookService.patchUpdateBookById(book.getBookId().toString(), book))
                .thenReturn(new ResponseEntity<>(book, HttpStatus.OK));

        ResponseEntity<Book> expectedBook = bookController.patchUpdateBookById(book.getBookId().toString(), book);

        assertNotNull(expectedBook);
        assertEquals(HttpStatus.OK, expectedBook.getStatusCode());
        assertNotNull(expectedBook.getBody());
        assertEquals(book, expectedBook.getBody());

        verify(bookService).patchUpdateBookById(book.getBookId().toString(), book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void patchUpdateBookByIdCheckNewParametersTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);

        book.setBookTitle("New Title");
        book.setAuthor("New Author");
        book.setBookPrice(BigDecimal.valueOf(50.00));

        when(bookService.patchUpdateBookById(book.getBookId().toString(), book)).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));

        ResponseEntity<Book> expectedBook = bookController.patchUpdateBookById(book.getBookId().toString(), book);

        assertEquals(book, expectedBook.getBody());
        assertEquals(book.getBookTitle(), Objects.requireNonNull(expectedBook.getBody()).getBookTitle());
        assertEquals(book.getAuthor(), expectedBook.getBody().getAuthor());
        assertEquals(book.getBookPrice(), expectedBook.getBody().getBookPrice());

        verify(bookService).patchUpdateBookById(book.getBookId().toString(), book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void patchUpdateBookByIdNegativeTest() {

        when(bookService.getBookById(book.getBookId().toString())).thenReturn(null);

        ResponseEntity<Book> expectedBook = bookController.patchUpdateBookById(book.getBookId().toString(), book);

        assertNull(expectedBook);

        verify(bookService).patchUpdateBookById(book.getBookId().toString(), book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void deleteBookByBookIdPositiveTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);
        ResponseEntity<String> expectedBook = bookController.deleteBookByBookId(String.valueOf(book.getBookId()));

        assertNull(expectedBook);
    }

    @Test
    public void deleteBookByBookIdNegativeTest() {
        when(bookService.getBookById(book.getBookId().toString())).thenReturn(null);
        Book expectedBook = bookController.getBookByBookId(book.getBookId().toString());

        assertNull(expectedBook);
    }
}