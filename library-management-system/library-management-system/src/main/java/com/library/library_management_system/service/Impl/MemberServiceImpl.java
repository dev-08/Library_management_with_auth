package com.library.library_management_system.service.Impl;

import com.library.library_management_system.entities.Book;
import com.library.library_management_system.entities.Members;
import com.library.library_management_system.repository.BookRepository;
import com.library.library_management_system.repository.MemberRepository;
import com.library.library_management_system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class MemberServiceImpl implements MemberService {



    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;



    @Override
    public Members addMember(Members members) {

        if(members!=null){
        Set<Book> bookSet = new HashSet<>();
            if(members.getBorrowedBooks().size()>0){
                for(Book book:members.getBorrowedBooks()){
                   Book book1 =   bookRepository.findById(book.getBookID()).orElse(null);
                   if(book1!=null){
                            bookSet.add(book1);
                   }
                }
            }


            members.setBorrowedBooks(bookSet);

//            members.getBorrowedBooks().forEach(book -> {
//                book.addMember(members);
//            });

//            for (Book book : members.getBorrowedBooks()) {
//                // Set the association on the book's side
//                book.addMember(members);
//                bookRepository.save(book);  // Save the book with the updated association
//            }
            return memberRepository.save(members);
        }
        return null;


    }

    @Override
    public List<Members> getMembers() {
        return memberRepository.findAll();
    }
}
