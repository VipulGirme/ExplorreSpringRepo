package com.example.demo.repository;

import com.example.demo.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthoRepository extends MongoRepository<Author, String> {

}
