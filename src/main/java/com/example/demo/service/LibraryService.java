package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Lend;
import com.example.demo.model.LendStatus;
import com.example.demo.model.Member;
import com.example.demo.model.MemberStatus;
import com.example.demo.model.request.AuthorCreationRequest;
import com.example.demo.model.request.BookCreationRequest;
import com.example.demo.model.request.BookLendRequest;
import com.example.demo.model.request.MemberCreationRequest;
import com.example.demo.repository.AuthoRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LendRepository;
import com.example.demo.repository.MemberRepository;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class LibraryService {

	@Autowired
	private AuthoRepository authorRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private LendRepository lendRepository;

	@Autowired
	private BookRepository bookRepository;

	public Book readBookById(String id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException("Cant find any book under given ID");
	}

	public List<Book> readBooks() {
		return bookRepository.findAll();
	}

	public Book readBook(String isbn) {
		Optional<Book> book = bookRepository.findByIsbn(isbn);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException("Cant find any book under given ISBN");
	}

	public Book createBook(BookCreationRequest book) {
		Optional<Author> author = authorRepository.findById(book.getAuthorId());
		if (!author.isPresent()) {
			throw new EntityNotFoundException("Author Not Found");
		}
		Book bookToCreate = new Book();
		BeanUtils.copyProperties(book, bookToCreate);
		bookToCreate.setAuthor(author.get());
		return bookRepository.save(bookToCreate);
	}

	public void deleteBook(String id) {
		bookRepository.deleteById(id);
	}

	public Member createMember(MemberCreationRequest request) {
		Member member = new Member();
		BeanUtils.copyProperties(request, member);
		member.setStatus(MemberStatus.ACTIVE);
		return memberRepository.save(member);
	}

	public Member updateMember(String id, MemberCreationRequest request) {
		Optional<Member> optionalMember = memberRepository.findById(id);
		if (!optionalMember.isPresent()) {
			throw new EntityNotFoundException("Member not present in the database");
		}
		Member member = optionalMember.get();
		member.setLastName(request.getLastName());
		member.setFirstName(request.getFirstName());
		return memberRepository.save(member);
	}

	public Author createAuthor(AuthorCreationRequest request) {
		Author author = new Author();
		BeanUtils.copyProperties(request, author);
		return authorRepository.save(author);
	}
	
	public List<Book> getBookbyAuthorName(String authorName) {
		return authorRepository.getBooksByAuthor(authorName);
	}

	public List<String> lendABook(BookLendRequest request) {

		Optional<Member> memberForId = memberRepository.findById(request.getMemberId());
		if (!memberForId.isPresent()) {
			throw new EntityNotFoundException("Member not present in the database");
		}

		Member member = memberForId.get();
		if (member.getStatus() != MemberStatus.ACTIVE) {
			throw new RuntimeException("User is not active to proceed a lending.");
		}

		List<String> booksApprovedToBurrow = new ArrayList<>();

		request.getBookIds().forEach(bookId -> {

			Optional<Book> bookForId = bookRepository.findById(bookId);
			if (!bookForId.isPresent()) {
				throw new EntityNotFoundException("Cant find any book under given ID");
			}

			Optional<Lend> burrowedBook = lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BURROWED);
			if (!burrowedBook.isPresent()) {
				booksApprovedToBurrow.add(bookForId.get().getName());
				Lend lend = new Lend();
				lend.setMember(memberForId.get());
				lend.setBook(bookForId.get());
				lend.setStatus(LendStatus.BURROWED);
				lend.setStartOn(Instant.now());
				lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
				lendRepository.save(lend);
			}

		});
		return booksApprovedToBurrow;
	}
}
