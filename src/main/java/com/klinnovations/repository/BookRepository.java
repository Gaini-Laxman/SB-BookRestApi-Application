package com.klinnovations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klinnovations.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
