package com.bogoliubova.training_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
   // @Type(type = "uuid-char")
    @Column(name = "book_id", columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)

    private UUID bookId;

    @Column(name = "b_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "b_price")
    private double bookPrice;

//    @OneToOne
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private Direction direction;

//    @OneToMany//(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private List<Direction> directions;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "direction_id")
    private List<Direction> directions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookPrice == book.bookPrice && Objects.equals(bookId, book.bookId) && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookTitle, author, bookPrice);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", author='" + author + '\'' +
                ", bookPrice=" + bookPrice +
                ", directions=" + directions +
                '}';
    }
}


//create-tables-changelog.xml
//insert_test_data_changelog_1.xml

//CREATE TRIGGER IF NOT
// EXISTS before_insert_users
// BEFORE INSERT ON oxatrade.users
// FOR EACH ROW BEGIN
// IF NEW.id IS NULL THEN
// SET NEW.id = (UUID_TO_BIN(UUID()));
// END IF; END;

//CREATE TABLE IF NOT EXISTS oxatrade.users (
// id BINARY(16) NOT NULL,
// email varchar(64) NOT NULL,
// password varchar(2048) NOT NULL,
// phone varchar(25) DEFAULT NULL,
// provider varchar(25) DEFAULT NULL COMMENT 'google or facebook, etc.',
// user_role varchar(32) NOT NULL DEFAULT 'USER',
// first_name varchar(64) NOT NULL,
// last_name varchar(64) DEFAULT NULL,
// title varchar(32) DEFAULT NULL,
// enabled BOOLEAN NOT NULL DEFAULT '0',
// created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
// updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  PRIMARY KEY (id),  UNIQUE KEY email (email)) ENGINE=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Table of users';

//    @GetMapping("/{id}")//localhost:8080/app/account/30633730-6166-6131-2d63-3635342d3437(не работает)
//    public Account getAccountByID(@PathVariable("id") UUID id) {
//        Account account = accountService.getAccById(id);
//        return (Account) Hibernate.unproxy(account);
//
//        Nikolay Osetrov 12:27
//        @Id
//        @UuidGenerator private UUID id;

///////////////////////////////////////////

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "book_id", columnDefinition = "BINARY(36)")
//    @JsonSerialize(using = ToStringSerializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "00000000-0000-0000-0000-000000000000")
//    @JsonFormat(pattern = "00000000-0000-0000-0000-000000000000")

//    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
//    private UUID bookId;

//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID",
//            strategy = "com.bogoliubova.training_service.generator.UuidTimeSequenceGenerator")
//    @Column(name = "book_id")




//    @GetMapping(value = "/id_book/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE) //
//    public Book getBookById(@PathVariable("book_id") UUID id) {
//        return bookService.getBookById(String.valueOf(id));
//    }

//    @Autowired
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

//        @GetMapping("/id_book/{book_id}")
//    public ResponseEntity<Book> getBookById(@PathVariable("book_id") UUID id) {
//        Book book = bookService.getBookById(UUID.fromString(String.valueOf(id)));
//        return ResponseEntity.ok(book);
//    }