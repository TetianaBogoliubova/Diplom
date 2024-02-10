package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Services;
import com.bogoliubova.training_service.service.interf.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookControllerTest {
    @InjectMocks
    private static BookController bookController;
    @Mock
    private BookService bookService;
    private static final UUID bookId = UUID.fromString("298e7601-e47a-5cd9-f387-125124058224");
    private static final String bookTitle = "BookTitle";
    private static final String author = " Author";
    private static final BigDecimal bookPrice = new BigDecimal("100.0");
    private static final List<Direction> directions = new ArrayList<>();
    @Mock
    private static Services services;
    private static Book book;

    @Before
    public void setUp() {

        bookService = Mockito.mock(BookService.class);
        book = new Book();
        services = Mockito.mock(Services.class);

        book.setBookId(bookId);
        book.setBookTitle(bookTitle);
        book.setAuthor(author);
        book.setBookPrice(bookPrice);
        book.setDirections(directions);
        //book.getServices();

        bookController = new BookController(bookService);
    }

    @Test
    public void getBookByBookIdTest() {
        Mockito.when(bookService.getBookById(bookId.toString())).thenReturn(book);
        Mockito.when(services.getServiceId()).thenReturn(UUID.randomUUID());
        Book result = bookController.getBookByBookId(bookId.toString());

        assertNotNull(result);
        assertEquals(bookId, result.getBookId());
        assertEquals(bookTitle, result.getBookTitle());
        assertEquals(author, result.getAuthor());
        assertEquals(bookPrice, result.getBookPrice());
    }

    @Test
    public void createBook() {
    }

    @Test
    public void updateBookById() {
    }

    @Test
    public void patchUpdateBookById() {
    }

    @Test
    public void deleteBookByBookId() {
    }
}
//
//public class CustomerControllerTest {
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @Mock
//    private CustomerService customerService;
//
//    private static final UUID customerId = UUID.fromString("483e5800-e40a-2cd3-f678-617223078864");
//    private static final String firstName = "John";
//    private static final String lastName = "Doe";
//    private static final String cusEmail = "john.doe@example.com";
//    private static final Location location = new Location();  // Инициализируйте Location по вашим требованиям
//    private static final List<Direction> directions = new ArrayList<>();  // Инициализируйте List<Direction> по вашим требованиям
//    private static final Customer mockCustomer = new Customer();
//
//    @Before
//    public void setUp() {
//        mockCustomer.setCustomerId(customerId);
//        mockCustomer.setFirstName(firstName);
//        mockCustomer.setLastName(lastName);
//        mockCustomer.setCusEmail(cusEmail);
//        mockCustomer.setLocation(location);
//        mockCustomer.setDirections(directions);
//
//        // Инициализация моков и настройка поведения
//        when(customerService.getCustomerById(customerId.toString())).thenReturn(mockCustomer);
//    }
//
//    @Test
//    public void testGetCustomerByCustomerId() {
//        // Act
//        Customer result = customerController.getCustomerByCustomerId(customerId.toString());
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(customerId, result.getCustomerId());
//        assertEquals(firstName, result.getFirstName());
//        assertEquals(lastName, result.getLastName());
//        assertEquals(cusEmail, result.getCusEmail());
//        assertEquals(location, result.getLocation());
//        assertEquals(directions, result.getDirections());
//    }
//}