package com.simonamilosheska.services.interfaces;

import com.simonamilosheska.requests.BookUpdateRequest;
import com.simonamilosheska.responses.BookDto;
import com.simonamilosheska.models.Author;
import com.simonamilosheska.models.Book;
import com.simonamilosheska.requests.BookRequest;

import java.util.List;

public interface BookService {

  List<BookDto> getAllBooks();

  BookDto findBookById(Integer id);

  List<BookDto> findAllBooksByAuthor(Author author);

  Book saveBook(BookRequest bookRequest);

  void deleteBookById(Integer id);

  void editBookById(Integer id, BookRequest bookRequest);
  void updateBookCopies(Integer id, BookUpdateRequest bookUpdateRequest);

  Book getBookIfPresentOrError(Integer id);

  void validateBookNameAndAuthor(String bookName, Author author);
}
