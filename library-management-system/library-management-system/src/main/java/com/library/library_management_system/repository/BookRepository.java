package com.library.library_management_system.repository;

import com.library.library_management_system.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository  extends JpaRepository<Book, Integer>{




}
