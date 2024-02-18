package com.bogoliubova.training_service.service.implTest;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookServiceImplTest {
    @InjectMocks
    private static BookServiceImpl bookService;
    //    @Mock
//    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    private static final UUID bookId = UUID.fromString("298e7601-e47a-5cd9-f387-125124058224");
    private static final String bookTitle = "BookTitle";
    private static final String author = " Author";
    private static final BigDecimal bookPrice = new BigDecimal("100.00");
    @Mock
    private static List<Direction> directions = new ArrayList<>();
    @Mock
    private static Services services;
    private static Book book;

    @Before
    public void setUp() {

        // bookService = Mockito.mock(BookService.class);
        bookRepository = Mockito.mock(BookRepository.class);
        book = new Book();
        services = Mockito.mock(Services.class);
        directions = Collections.singletonList(Mockito.mock(Direction.class));

        book.setBookId(bookId);
        book.setBookTitle(bookTitle);
        book.setAuthor(author);
        book.setBookPrice(bookPrice);
        book.setDirections(directions);
        book.getServices();
        // bookController = new BookController(bookService);
    }

    @Test
    public void getBookByIdTest() {
        Book result = bookRepository.findBookByBookId(bookId);

        assertNotNull(result);
        assertEquals(bookId, result.getBookId());
        assertEquals(bookTitle, result.getBookTitle());
        assertEquals(author, result.getAuthor());
        assertEquals(bookPrice, result.getBookPrice());
    }


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