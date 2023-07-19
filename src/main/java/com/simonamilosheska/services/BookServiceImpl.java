package com.simonamilosheska.services;

import com.simonamilosheska.responses.BookDto;
import com.simonamilosheska.exceptions.AlreadyExistEntityException;
import com.simonamilosheska.exceptions.NotExistEntityException;
import com.simonamilosheska.models.Author;
import com.simonamilosheska.models.Book;
import com.simonamilosheska.repositories.BookRepository;
import com.simonamilosheska.requests.BookRequest;
import com.simonamilosheska.services.interfaces.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

  @Autowired
  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<BookDto> getAllBooks() {
    return bookRepository.findAll().stream().map(BookDto::new).collect(Collectors.toList());
  }

  @Override
  public BookDto findBookById(Integer id) {
    Book book = getBookIfPresentOrError(id);
    return new BookDto(book);
  }

  @Override
  public List<BookDto> findAllBooksByAuthor(Author author) {
    return bookRepository.findBookByAuthor(author).stream().map(BookDto::new).collect(Collectors.toList());
  }

  @Override
  public Book saveBook(BookRequest bookRequest) {
    validateBookNameAndAuthor(bookRequest.getName(), bookRequest.getAuthor());
    Book book = new Book(bookRequest.getName(), bookRequest.getAuthor(), bookRequest.getDateOfPublishing(),
                         bookRequest.getNumberOfCopies());
    return bookRepository.save(book);
  }

  @Override
  public void deleteBookById(Integer id) {
    getBookIfPresentOrError(id);
    bookRepository.deleteById(id);
  }

  @Override
  public void editBookById(Integer id, BookRequest bookRequest) {
    Book book = getBookIfPresentOrError(id);
    book.setName(bookRequest.getName());
    book.setNumberOfCopies(bookRequest.getNumberOfCopies());
    book.setDateOfPublishing(bookRequest.getDateOfPublishing());
    book.setAuthor(bookRequest.getAuthor());
    bookRepository.save(book);
  }

  @Override
  public Book getBookIfPresentOrError(Integer id) {
    Optional<Book> optionalBook = bookRepository.findById(id);
    return optionalBook
      .orElseThrow(() -> {
        log.error(String.format("Book with id [%d] not found in database", id));
        return new NotExistEntityException(String.format("Book with id: %d doesn't exist", id));
      });
  }

  @Override
  public void validateBookNameAndAuthor(String bookName, Author author) {
    Optional<Book> optionalBook = bookRepository.findBookByNameAndAuthor(bookName, author);
    if (optionalBook.isPresent()) {
      log.error(String.format("Book with name [%s] and author name [%s] already exist  in database", bookName,
                              author.getName()));
      throw new AlreadyExistEntityException(
        String.format("Book with name [%s] and author name [%s] already exist, if you want to add copy of book select addBookCopy", bookName, author.getName()));
    }
  }
}
