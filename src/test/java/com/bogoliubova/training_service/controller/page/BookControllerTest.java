package com.bogoliubova.training_service.controller.page;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.entity.Direction;
import com.bogoliubova.training_service.entity.Services;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
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
    public void createBookTest() {
        when(bookService.createNewBook(any(Book.class))).thenReturn(book);
        Book newBook = bookController.createBook(book);

        assertNotNull(newBook);
        assertEquals(book, newBook);
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
//Этот тест создает мок `BookService` и использует `when` для настройки возвращаемого значения при вызове метода
// `updateBook` с ожидаемыми аргументами. Затем он вызывает метод `updateBookById` контроллера и проверяет, что
// возвращается ожидаемый объект ResponseEntity с кодом состояния `HttpStatus.OK` и обновленной книгой в теле ответа.

//    @Test
//    public void updateBookByIdNegativeTest() {
//
//        when(bookService.getBookById(String.valueOf(book.getBookId()))).thenReturn(null);
//        Book result = bookController.getBookByBookId(String.valueOf(book.getBookId()));
//        ResponseEntity<Book> updateResult = bookController.updateBookById(result, book.getBookId().toString());
//        assertNotEquals(book, result);
//        assertNull(result);
//        assertEquals(HttpStatus.NOT_FOUND, updateResult.getStatusCode()); // Проверка HTTP-статуса NOT_FOUND
//        assertNull(updateResult.getBody()); // Проверка, что в ответе нет тела
//
//
//    }
//    // Проверка, что свойства книги были действительно обновлены в репозитории:
//    //Ваш текущий метод updateBook возвращает ResponseEntity<Book>, и вы можете использовать это для проверки успешного обновления в репозитории.
//
//
//    @Test
//    public void updateBookByIdInconsistencyOfOldAndNewParametersTest() {
//        when(bookService.getBookById(book.getBookId().toString())).thenReturn(book);
//        Book updateBook = new Book();
//
//
//        updateBook.setBookTitle("New Title");
//        updateBook.setAuthor("New Author");
//        updateBook.setBookPrice(BigDecimal.valueOf(50.00));
//        updateBook.setDirections(directions);
//
//        ResponseEntity<Book> updateResult = bookController.updateBookById(updateBook, book.getBookId().toString());
//        //assertNotNull(updateResult);
//
//        assertNotEquals(book, updateResult.getBody());
//        assertNotEquals(book.getAuthor(), updateResult.getBody().getAuthor());
//        assertNotEquals(book.getBookPrice(), updateResult.getBody().getBookPrice());
//
//    }

//    @Test
//    public void updateNonexistentBookById() {
//        // Arrange
//        when(bookService.getBookById(book.getBookId().toString())).thenReturn(null);
//
//
//        ResponseEntity<Book> updateResult = bookController.updateBookById(book, book.getBookId().toString());
//
//
//        assertEquals(HttpStatus.NOT_FOUND, updateResult.getStatusCode()); // Проверка HTTP-статуса NOT_FOUND
//        assertNull(updateResult.getBody()); // Проверка, что в ответе нет тела
//    }


    @Test
    public void patchUpdateBookById() {
    }

    @Test
    public void deleteBookByBookId() {
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

}

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

////////////////////////////////////////////////////
//    public void setUp() {
////
////        bookService = Mockito.mock(BookService.class);
////        bookRepository = Mockito.mock(BookRepository.class);
//        book = new Book();
//        services = Mockito.mock(Services.class);
//        directions = Collections.singletonList(Mockito.mock(Direction.class));
//
//        book.setBookId(bookId);
//        book.setBookTitle(bookTitle);
//        book.setAuthor(author);
//        book.setBookPrice(bookPrice);
//        book.setDirections(directions);
//        book.getServices();
//    }
//
//        bookController = new BookController(bookService);
//    }

//    @Test
//    public void getBookByBookIdTest2() {
//        UUID bookId = UUID.fromString("298e7601-e47a-5cd9-f387-125124058224");
//        Book expectedBook = new Book();
//        // book.setBookId(bookId);
//        expectedBook.setBookTitle(bookTitle);
//        expectedBook.setAuthor(author);
//        expectedBook.setBookPrice(bookPrice);
//        expectedBook.setDirections(directions);
//        expectedBook.getServices();
//
//        when(bookService.getBookById(bookId.toString())).thenReturn(expectedBook);
//
//        Book actualBook = bookController.getBookByBookId(String.valueOf(bookId));
//        assertEquals(expectedBook, actualBook);
//        verify(bookService, times(1)).getBookById(String.valueOf(bookId));
//
//    }
