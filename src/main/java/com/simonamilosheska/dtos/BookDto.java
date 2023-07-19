package com.simonamilosheska.dtos;

import com.simonamilosheska.models.Author;
import com.simonamilosheska.models.Book;

import java.time.LocalDate;

public class BookDto {

  private int id;
  private String name;
  private AuthorDto author;
  private LocalDate dateOfPublishing;
  private int numberOfCopies;

  public BookDto(Book book) {
    this.id = book.getId();
    this.name = book.getName();
    this.author = new AuthorDto(book.getAuthor());
    this.dateOfPublishing = book.getDateOfPublishing();
    this.numberOfCopies = book.getNumberOfCopies();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public AuthorDto getAuthor() {
    return author;
  }

  public LocalDate getDateOfPublishing() {
    return dateOfPublishing;
  }

  public int getNumberOfCopies() {
    return numberOfCopies;
  }
}
