package com.simonamilosheska.requests;

import com.simonamilosheska.models.Author;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookRequest {

  @Pattern(regexp = "[A-Za-z\\s]+", message = "Invalid book name must not be null ")
  private String name;
  private Author author;
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date , must be yyyy-MM-dd")
  private String dateOfPublishing;
  private int numberOfCopies;
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


  public String getName() {
    return name;
  }

  public Author getAuthor() {
    return author;
  }

  public LocalDate getDateOfPublishing() {
    return LocalDate.parse(dateOfPublishing, formatter);
  }

  public int getNumberOfCopies() {
    return numberOfCopies;
  }
}
