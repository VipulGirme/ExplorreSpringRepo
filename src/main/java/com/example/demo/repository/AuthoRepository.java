package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Author;
import com.example.demo.model.Book;

public interface AuthoRepository extends MongoRepository<Author, String> {
	
	  @Query("{firstName : ?0}")    // SQL Equivalent : SELECT * FROM BOOK where author = ?
      List<Book> getBooksByAuthor(String author);

}
