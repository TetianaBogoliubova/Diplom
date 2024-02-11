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
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookControllerTest {
    @InjectMocks
    private static BookController bookController;
    @Mock
    private BookService bookService;
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

        bookService = Mockito.mock(BookService.class);
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

        bookController = new BookController(bookService);
    }

    @Test
    public void getBookByBookIdTest() {
        when(bookService.getBookById(bookId.toString())).thenReturn(book);
        when(services.getServiceId()).thenReturn(UUID.randomUUID());
        Book result = bookController.getBookByBookId(bookId.toString());

        assertNotNull(result);
        assertEquals(bookId, result.getBookId());
        assertEquals(bookTitle, result.getBookTitle());
        assertEquals(author, result.getAuthor());
        assertEquals(bookPrice, result.getBookPrice());
    }

    @Test
    public void createBook() {
        when(bookService.createNewBook(any(Book.class))).thenReturn(book);
        Book newBook = bookController.createBook(book);

        assertNotNull(newBook);
        assertEquals(book, newBook);
    }

    @Test
    public void updateBookByIdInconsistencyOfOldAndNewParametersTest() {
        when(bookService.getBookById(bookId.toString())).thenReturn(book);
        Book result = bookController.getBookByBookId(bookId.toString());

       // assertNotNull(result);
       // assertEquals(book, result);//книга, которую ищем, совпадает с той, что вернулась по id

        result.setBookTitle("New Title");
        result.setAuthor("New Author");
        result.setBookPrice(BigDecimal.valueOf(50.00));
        result.setDirections(directions);


        assertNotNull(result);
        assertNotEquals(bookTitle, result.getBookTitle());
        assertNotEquals(author, result.getAuthor());
        assertNotEquals(bookPrice, result.getBookPrice());
    }

//    @Test
//    public void updateBookByIdPositiveTest() {
//        when(bookService.getBookById(bookId.toString())).thenReturn(book);
//        // when(bookRepository.findBookByBookId(any(UUID.class))).thenReturn(book);
//        Book result = bookController.getBookByBookId(bookId.toString());
//
//        assertNotNull(result);
//        assertEquals(book, result);
//
//        result.setBookTitle("New Title1");
//        result.setAuthor("New Author1");
//        result.setBookPrice(BigDecimal.valueOf(50.00));
//        result.setDirections(directions);
//
//        ResponseEntity<Book> updateResult = bookController.updateBookById(result, bookId.toString());
//
//        assertNotNull(updateResult);
//        assertEquals(HttpStatus.OK, updateResult.getStatusCode());
//        assertNotNull(updateResult.getBody());
//        assertEquals(result, updateResult.getBody());
//
//    }
//
//
//
//
//
//    @Test
//    public void updateBookByIdNegativeTest() {
//        when(bookService.getBookById(bookId.toString())).thenReturn(null);
//
//        ResponseEntity<Book> updateResult = bookController.updateBookById(new Book(), bookId.toString());
//
//        assertEquals(HttpStatus.NOT_FOUND, updateResult.getStatusCode()); // Проверка HTTP-статуса NOT_FOUND
//        assertNull(updateResult.getBody()); // Проверка, что в ответе нет тела
//        }
//        Проверка, что свойства книги были действительно обновлены в репозитории:
//
//        Ваш текущий метод updateBook возвращает ResponseEntity<Book>, и вы можете использовать это для проверки успешного обновления в репозитории.
//


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


//    @Test
//    public void updateBookById() {
//        // Arrange
//        when(bookService.getBookById(bookId.toString())).thenReturn(book);
//
//        // Act
//        Book result = bookController.getBookByBookId(bookId.toString());
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(book, result); // Проверка, что полученная книга соответствует ожидаемой
//
//        // Update book properties
//        result.setBookTitle("New Title");
//        result.setAuthor("New Author");
//        result.setBookPrice(BigDecimal.valueOf(50.00));
//        result.setDirections(directions);
//
//        // Act
//        ResponseEntity<Book> updateResult = bookController.updateBook(result, bookId.toString());
//
//        // Assert
//        assertEquals(HttpStatus.OK, updateResult.getStatusCode()); // Проверка успешного HTTP-статуса
//        assertNotNull(updateResult.getBody()); // Проверка, что в ответе есть тело
//        assertEquals(result, updateResult.getBody()); // Проверка, что обновленная книга соответствует ожидаемой
//    }
//    Проверка неуспешного обновления для несуществующей книги:
//
//        java
//        Copy code
//@Test
//public void updateNonexistentBookById() {
//        // Arrange
//        when(bookService.getBookById(bookId.toString())).thenReturn(null);
//
//        // Act
//        ResponseEntity<Book> updateResult = bookController.updateBook(new Book(), bookId.toString());
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, updateResult.getStatusCode()); // Проверка HTTP-статуса NOT_FOUND
//        assertNull(updateResult.getBody()); // Проверка, что в ответе нет тела
//        }



//        Проверка, что свойства книги были действительно обновлены в репозитории:
//
//        Ваш текущий метод updateBook возвращает ResponseEntity<Book>, и вы можете использовать это для проверки успешного обновления в репозитории.
//
//        java
//        Copy code
//@Test
//public void checkRepositoryUpdate() {
//        // Arrange
//        when(bookService.getBookById(bookId.toString())).thenReturn(book);
//
//        // Act
//        Book result = bookController.getBookByBookId(bookId.toString());
//
//        // Assert
//        assertNotNull(result);
//
//        // Update book properties
//        result.setBookTitle("New Title");
//        result.setAuthor("New Author");
//        result.setBookPrice(BigDecimal.valueOf(50.00));
//        result.setDirections(directions);
//
//        // Act
//        ResponseEntity<Book> updateResult = bookController.updateBook(result, bookId.toString());
//
//        // Assert
//        assertEquals(HttpStatus.OK, updateResult.getStatusCode()); // Проверка успешного HTTP-статуса
//
//        // Проверка, что свойства книги были действительно обновлены в репозитории
//        verify(bookRepository, times(1)).save(result);
//        }