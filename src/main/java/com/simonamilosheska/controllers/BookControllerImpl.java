package com.simonamilosheska.controllers;

import com.simonamilosheska.controllers.interfaces.BookController;
import com.simonamilosheska.dtos.BookDto;
import com.simonamilosheska.models.Book;
import com.simonamilosheska.requests.BookRequest;

import com.simonamilosheska.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class BookControllerImpl implements BookController {

  BookServiceImpl bookServiceImpl;
  AuthorControllerImpl authorControllerImpl;

  @Autowired
  public BookControllerImpl(BookServiceImpl bookServiceImpl, AuthorControllerImpl authorControllerImpl) {
    this.bookServiceImpl = bookServiceImpl;
    this.authorControllerImpl = authorControllerImpl;
  }

  @GetMapping("/books")
  @Override
  public ResponseEntity<List<BookDto>> getAllBooks() {
    List<BookDto> books = bookServiceImpl.getAllBooks();
    return ResponseEntity.ok(books);
  }

  @RequestMapping("books/{bookId}")
  @Override
  public ResponseEntity<BookDto> getBookById(@PathVariable Integer bookId) {
    BookDto book = bookServiceImpl.findBookById(bookId);
    return ResponseEntity.ok(book);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN','ADMIN')")
  @PostMapping("/books")
  @Override
  public ResponseEntity<Void> createBook(@RequestBody @Valid BookRequest bookRequest) {
    Book book = bookServiceImpl.saveBook(bookRequest);

    URI location = UriComponentsBuilder.fromUriString("/books/{id}")
                                       .buildAndExpand(book.getId())
                                       .toUri();

    return ResponseEntity.created(location).build();
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN','ADMIN')")
  @PutMapping("/books/{bookId}")
  @Override
  public ResponseEntity<Void> updateBook(@RequestBody @Valid BookRequest bookRequest, @PathVariable Integer bookId) {
    bookServiceImpl.editBookById(bookId, bookRequest);
    return ResponseEntity.noContent().build();
  }
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/books/{bookId}")
  @Override
  public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
    bookServiceImpl.deleteBookById(bookId);
    return ResponseEntity.noContent().build();
  }
}
