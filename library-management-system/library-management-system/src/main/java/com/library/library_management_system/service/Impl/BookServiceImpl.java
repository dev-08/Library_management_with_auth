package com.library.library_management_system.service.Impl;

import com.library.library_management_system.entities.Book;
import com.library.library_management_system.entities.Members;
import com.library.library_management_system.repository.BookRepository;
import com.library.library_management_system.repository.MemberRepository;
import com.library.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {

            @Autowired
            private BookRepository bookRepository;

            @Autowired
            private MemberRepository memberRepository;

            public Book addBook(Book book){
                if(book!=null){

                    book.getMembers().forEach(members -> {
                        members.addBook(book);
                    });
//                    for (Members member : book.getMembers()) {
//                        // Set the association on the member's side
//                        member.addBook(book);
//                        memberRepository.save(member);  // Save the member with the updated association
//                    }
                   return bookRepository.save(book);
                }
                return null;
            }

    @Override
    public List<Book> getAllBooks() {

               return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
            Book book = bookRepository.findById(id).get();
            if(book!=null){
                return book;
            }
            return null;
    }


    public Book deleteBookById(int id){
                Book book = bookRepository.findById(id).get();
                if(book!=null){
                    bookRepository.delete(book);
                    return book;
                }
                return null;

    }


}
