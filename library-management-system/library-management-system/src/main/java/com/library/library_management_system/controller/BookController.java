package com.library.library_management_system.controller;


import com.library.library_management_system.entities.Book;
import com.library.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class BookController{

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<?> addbook(@RequestBody Book book){
        Book book1 = bookService.addBook(book);
        return ResponseEntity.ok(book1);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
            List<Book> books = bookService.getAllBooks();
            if(books.isEmpty()){
                return ResponseEntity.ok("library is empty");
            }
            return ResponseEntity.ok(books);
    }
    @GetMapping("/book/{id}")
    public  ResponseEntity<?> getBookById(@PathVariable int id){
        Book book = bookService.getBookById(id);

        if(book == null){
            return ResponseEntity.ok("book not found");
        }
        return ResponseEntity.ok(book);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable int id){
        Book book = bookService.deleteBookById(id);
            if(book == null){
                return ResponseEntity.ok("book not found");
            }
            return ResponseEntity.ok(book);
    }
}
