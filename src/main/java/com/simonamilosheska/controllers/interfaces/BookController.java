package com.simonamilosheska.controllers.interfaces;

import com.simonamilosheska.dtos.BookDto;
import com.simonamilosheska.requests.BookRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookController {

  ResponseEntity<List<BookDto>> getAllBooks();

  ResponseEntity<BookDto> getBookById(Integer bookId);

  ResponseEntity<Void> createBook(BookRequest bookRequest);

  ResponseEntity<Void> updateBook(BookRequest bookRequest, Integer bookId);

  ResponseEntity<Void> deleteBook(Integer bookId);
}