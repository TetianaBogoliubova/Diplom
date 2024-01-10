package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Book;

public interface BookService {
    Book getBookById(String id);
}
