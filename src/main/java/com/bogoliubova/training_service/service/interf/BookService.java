package com.bogoliubova.training_service.service.interf;

import com.bogoliubova.training_service.entity.Book;
import java.util.Map;


public interface BookService {
    Book getBookById(String id);

    Book createNewBook(Book book);

    Book updateBook(Book existingBook);

    boolean deleteBookById(String bookId);

    Book patchUpdateBook(String bookId, Map<String, Object> updates);
}


// Book patchUpdateBook(String bookId,String bookTitle, String author, Double bookPrice);