package com.example.demo.repository;

import com.example.demo.model.Book;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
}