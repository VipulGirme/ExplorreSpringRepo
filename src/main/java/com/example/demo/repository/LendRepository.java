package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Book;
import com.example.demo.model.LendStatus;
import com.example.demo.model.Lend;

public interface LendRepository extends MongoRepository<Lend, String> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
