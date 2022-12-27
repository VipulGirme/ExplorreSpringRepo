package com.example.demo.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Lend {

	@Id
	private String id;
	private LendStatus status;
	private Instant startOn;
	private Instant dueOn;
	@DBRef
	private Book book;
	@DBRef
	private Member member;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LendStatus getStatus() {
		return status;
	}

	public void setStatus(LendStatus status) {
		this.status = status;
	}

	public Instant getStartOn() {
		return startOn;
	}

	public void setStartOn(Instant startOn) {
		this.startOn = startOn;
	}

	public Instant getDueOn() {
		return dueOn;
	}

	public void setDueOn(Instant dueOn) {
		this.dueOn = dueOn;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
