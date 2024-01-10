package com.bogoliubova.training_service.service.impl;

import com.bogoliubova.training_service.entity.Book;
import com.bogoliubova.training_service.repository.BookRepository;
import com.bogoliubova.training_service.service.interf.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findBookByBookId(UUID.fromString(bookId));
    }
}
