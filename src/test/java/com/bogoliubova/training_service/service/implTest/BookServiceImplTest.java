package com.bogoliubova.training_service.service.implTest;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
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
import java.util.*;

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
        //book.getServices();
    }

    @Test
    public void getBookByIdPositiveTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(book);

        Book expectedBook = bookServiceImpl.getBookById(book.getBookId().toString());

        assertEquals(expectedBook, book);
        assertNotNull(expectedBook);

        verify(bookRepository).findBookByBookId(book.getBookId());
    }

    @Test
    public void getBookByIdNegativeTest() {
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(null);

        Book expectedBook = bookServiceImpl.getBookById(book.getBookId().toString());

        assertNotEquals(book, expectedBook);
        assertNull(expectedBook);

        verify(bookRepository).findBookByBookId(book.getBookId());
    }

    @Test
    public void getBookByIdCheckAllParametersTest() {
        Book expectedBook = new Book();

        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(expectedBook);
        Book result = bookServiceImpl.getBookById(book.getBookId().toString());

        assertNotNull(result);
        assertEquals(expectedBook.getBookId(), result.getBookId());
        assertEquals(expectedBook.getBookTitle(), result.getBookTitle());
        assertEquals(expectedBook.getAuthor(), result.getAuthor());
        assertEquals(expectedBook.getBookPrice(), result.getBookPrice());

        verify(bookRepository).findBookByBookId(book.getBookId());
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void createNewBookPositiveTest() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book newBook = bookServiceImpl.createNewBook(book);

        assertNotNull(newBook);
        assertEquals(book, newBook);

        verify(bookRepository, times(1)).save(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void createNewBookCheckAllParametersTest() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book newBook = bookServiceImpl.createNewBook(book);

        assertNotNull(newBook.getBookId());
        assertNotNull(newBook.getBookTitle());
        assertNotNull(newBook.getAuthor());
        assertNotNull(newBook.getBookPrice());
        assertNotNull(newBook.getDirections());

        verify(bookRepository, times(1)).save(book);
        verifyNoMoreInteractions(bookRepository);
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

        ResponseEntity<Book> expectedBook = bookServiceImpl.updateBook(updateBook, book.getBookId().toString());

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

        Book updateBook = new Book();

        updateBook.setBookTitle("New Title");
        updateBook.setAuthor("New Author");
        updateBook.setBookPrice(BigDecimal.valueOf(40.00));
        updateBook.setDirections(directions);

        when(bookRepository.save(any(Book.class))).thenReturn(updateBook);

        ResponseEntity<Book> updateResult = bookServiceImpl.updateBook(updateBook, book.getBookId().toString());

        assertNotEquals(book, updateResult.getBody());
        assertEquals("New Title", updateResult.getBody().getBookTitle());
        assertEquals("New Author", updateResult.getBody().getAuthor());
        assertEquals(BigDecimal.valueOf(40.00), updateResult.getBody().getBookPrice());

        verify(bookRepository, times(1)).findBookByBookId(book.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void updateBookByIdNegativeTest() {
        when(bookRepository.findBookByBookId(any(UUID.class))).thenReturn(null);

        ResponseEntity<Book> updateResult = bookServiceImpl.updateBook(book, UUID.randomUUID().toString());

        assertEquals(HttpStatus.NOT_FOUND, updateResult.getStatusCode());
        assertNull(updateResult.getBody());
    }

    @Test
    public void patchUpdateBookByIdPositiveTest() {
        when(bookRepository.findById(book.getBookId())).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Map<String, Object> updates = new HashMap<>();
        updates.put("bookTitle", "New Title1");
        updates.put("author", "New Author1");
        updates.put("bookPriceB", BigDecimal.valueOf(50.00));

        ResponseEntity<Book> expectedBook = bookServiceImpl.patchUpdateBookById(book.getBookId().toString(), updates);
        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook.getBody());

        assertNotNull(expectedBook);
        assertEquals(HttpStatus.OK, expectedBook.getStatusCode());
        assertNotNull(expectedBook.getBody());

        verify(bookRepository, times(1)).findById(book.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void patchUpdateBookCheckNewParametersTest() {
        when(bookRepository.findById(book.getBookId())).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Map<String, Object> updates = new HashMap<>();
        updates.put("bookTitle", "New Title1");
        updates.put("author", "New Author1");
        updates.put("bookPrice", BigDecimal.valueOf(50.00));

        ResponseEntity<Book> expectedBook = bookServiceImpl.patchUpdateBookById(book.getBookId().toString(), updates);
        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook.getBody());

        assertEquals("New Title1", expectedBook.getBody().getBookTitle());
        assertEquals("New Author1", expectedBook.getBody().getAuthor());
        assertEquals(BigDecimal.valueOf(50.00), expectedBook.getBody().getBookPrice());

        verify(bookRepository, times(1)).findById(book.getBookId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void patchUpdateBookByIdNegativeTest() {
        when(bookRepository.findById(any(UUID.class))).thenReturn(null);

        ResponseEntity<Book> expectedBook = bookServiceImpl.updateBook(book, UUID.randomUUID().toString());

        assertEquals(HttpStatus.NOT_FOUND, expectedBook.getStatusCode());
        assertNull(expectedBook.getBody());
    }
}
