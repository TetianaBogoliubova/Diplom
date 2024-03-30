package com.bogoliubova.training_service.service.implTest;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    @Mock
    private BookRepository bookRepository = Mockito.mock(BookRepository.class);
    @Mock
    private List<Direction> directions = new ArrayList<>();
    @Mock
    private Services services = new Services();

    private Book book;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setBookId(UUID.fromString("298e7601-e47a-5cd9-f387-125124058224"));
        book.setBookTitle("The Line of Beauty");
        book.setAuthor("Sherri Miller");
        book.setBookPrice(BigDecimal.valueOf(13.50));
        book.setDirections(directions);
        book.setServices(services);
    }

    @Test
    public void getBookByIdPositiveTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);

        Book expectedBook = bookServiceImpl.getBookById(book.getBookId().toString());

        assertEquals(expectedBook, book);
        assertNotNull(expectedBook);

        verify(bookRepository).findBookByBookId(book.getBookId());
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void getBookByIdNegativeTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(null);

        Book expectedBook = bookServiceImpl.getBookById(book.getBookId().toString());

        assertNotEquals(book, expectedBook);
        assertNull(expectedBook);

        verify(bookRepository).findBookByBookId(book.getBookId());
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void getBookByIdCheckAllParametersTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);

        Book expectedBook = bookServiceImpl.getBookById(book.getBookId().toString());

        assertNotNull(expectedBook);
        assertEquals(book.getBookId(), expectedBook.getBookId());
        assertEquals(book.getBookTitle(), expectedBook.getBookTitle());
        assertEquals(book.getAuthor(), expectedBook.getAuthor());
        assertEquals(book.getBookPrice(), expectedBook.getBookPrice());

        verify(bookRepository).findBookByBookId(book.getBookId());
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void createNewBookPositiveTest() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book expectedBook = bookServiceImpl.createNewBook(book);

        assertNotNull(expectedBook);
        assertEquals(book, expectedBook);

        verify(bookRepository, times(1)).save(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void createNewBookCheckAllParametersTest() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book expectedBook = bookServiceImpl.createNewBook(book);

        assertNotNull(expectedBook.getBookId());
        assertNotNull(expectedBook.getBookTitle());
        assertNotNull(expectedBook.getAuthor());
        assertNotNull(expectedBook.getBookPrice());
        assertNotNull(expectedBook.getDirections());

        verify(bookRepository, times(1)).save(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void updateBookPositiveTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        book.setBookTitle("New Title1");
        book.setAuthor("New Author1");
        book.setBookPrice(BigDecimal.valueOf(50.00));
        book.setDirections(directions);
        book.setServices(services);

        ResponseEntity<Book> expectedBook = bookServiceImpl.updateBook(book, book.getBookId().toString());

        assertNotNull(expectedBook);
        assertEquals(HttpStatus.OK, expectedBook.getStatusCode());
        assertNotNull(expectedBook.getBody());

        verify(bookRepository, times(1)).findBookByBookId(book.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void updateBookCheckNewParametersTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        book.setBookTitle("New Title");
        book.setAuthor("New Author");
        book.setBookPrice(BigDecimal.valueOf(40.00));
        book.setDirections(directions);

        ResponseEntity<Book> expectedBook = bookServiceImpl.updateBook(book, book.getBookId().toString());

        assertEquals("New Title", Objects.requireNonNull(expectedBook.getBody()).getBookTitle());
        assertEquals("New Author", expectedBook.getBody().getAuthor());
        assertEquals(BigDecimal.valueOf(40.00), expectedBook.getBody().getBookPrice());

        verify(bookRepository, times(1)).findBookByBookId(book.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void updateBookByIdNegativeTest() {
        when(bookRepository.findBookByBookId(any(UUID.class))).thenReturn(null);

        ResponseEntity<Book> expectedBook = bookServiceImpl.updateBook(book, UUID.randomUUID().toString());

        assertEquals(HttpStatus.NOT_FOUND, expectedBook.getStatusCode());
        assertNull(expectedBook.getBody());
    }

    @Test
    public void patchUpdateBookByIdPositiveTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        book.setBookTitle("New Title1");
        book.setAuthor("New Author1");
        book.setBookPrice(BigDecimal.valueOf(50.00));

        ResponseEntity<Book> expectedBook = bookServiceImpl.patchUpdateBookById(book.getBookId().toString(), book);

        assertNotNull(expectedBook);
        assertEquals(HttpStatus.OK, expectedBook.getStatusCode());
        assertNotNull(expectedBook.getBody());

        verify(bookRepository, times(1)).findBookByBookId(book.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void patchUpdateBookCheckNewParametersTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        book.setBookTitle("New Title");
        book.setAuthor("New Author");
        book.setBookPrice(BigDecimal.valueOf(40.00));
        book.setDirections(directions);

        ResponseEntity<Book> expectedBook = bookServiceImpl.patchUpdateBookById(book.getBookId().toString(), book);

        assertEquals("New Title", Objects.requireNonNull(expectedBook.getBody()).getBookTitle());
        assertEquals("New Author", expectedBook.getBody().getAuthor());
        assertEquals(BigDecimal.valueOf(40.00), expectedBook.getBody().getBookPrice());

        verify(bookRepository, times(1)).findBookByBookId(book.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void patchUpdateBookByIdNegativeTest() {
        when(bookRepository.findById(book.getBookId())).thenReturn(null);

        ResponseEntity<Book> expectedBook = bookServiceImpl.patchUpdateBookById(UUID.randomUUID().toString(), book);

        assertEquals(HttpStatus.NOT_FOUND, expectedBook.getStatusCode());
        assertNull(expectedBook.getBody());
    }
}
