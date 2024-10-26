package com.library.library_management_system.service;


import com.library.library_management_system.entities.Book;

import java.util.List;

public interface BookService {


    Book addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(int id);
    Book deleteBookById(int id);
}
