package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Book;

import java.util.UUID;

public interface BookService {
    Book getBookById(String book_id);
}
