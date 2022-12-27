package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Member;
import com.example.demo.model.request.AuthorCreationRequest;
import com.example.demo.model.request.BookCreationRequest;
import com.example.demo.model.request.BookLendRequest;
import com.example.demo.model.request.MemberCreationRequest;
import com.example.demo.service.LibraryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;


	@GetMapping("/book")
	public ResponseEntity readBooks(@RequestParam(required = false) String isbn) {
		if (isbn == null) {
			return ResponseEntity.ok(libraryService.readBooks());
		}
		return ResponseEntity.ok(libraryService.readBook(isbn));
	}
	
	@GetMapping("/book/{bookId}")
    public ResponseEntity<Book> readBook (@PathVariable String bookId) {
        return ResponseEntity.ok(libraryService.readBookById(bookId));
    }
	
	@PostMapping("/book")
    public ResponseEntity<Book> createBook (@RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.createBook(request));
    }
	
	@DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook (@PathVariable String bookId) {
        libraryService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
	
	@PostMapping("/member")
    public ResponseEntity<Member> createMember (@RequestBody MemberCreationRequest request) {
        return ResponseEntity.ok(libraryService.createMember(request));
    }
	
	@PatchMapping("/member/{memberId}")
    public ResponseEntity<Member> updateMember (@RequestBody MemberCreationRequest request, @PathVariable String memberId) {
        return ResponseEntity.ok(libraryService.updateMember(memberId, request));
    }
	
	@PostMapping("/book/lend")
    public ResponseEntity<List<String>> lendABook(@RequestBody BookLendRequest bookLendRequests) {
        return ResponseEntity.ok(libraryService.lendABook(bookLendRequests));
    }
	
	@PostMapping("/author")
    public ResponseEntity<Author> createAuthor (@RequestBody AuthorCreationRequest request) {
        return ResponseEntity.ok(libraryService.createAuthor(request));
    }
}
