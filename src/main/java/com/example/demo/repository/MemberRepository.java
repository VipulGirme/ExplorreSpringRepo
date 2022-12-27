package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Member;

public interface MemberRepository extends MongoRepository<Member, String> {

}
