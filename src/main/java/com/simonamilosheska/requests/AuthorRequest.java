package com.simonamilosheska.requests;


import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AuthorRequest {

  @Pattern(regexp = "[A-Za-z\\s]+", message = "Invalid author name must, author name must not be null or contain numbers, try one more time")
  private String name;
  @Pattern(regexp = "^(.+)@(\\S+)$", message = "Invalid author email, author email must be with @")
  private String email;
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date of birth format, date of birth must be yyyy-MM-dd")
  private String dateOfBirth;
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getDateOfBirth() {
    return LocalDate.parse(dateOfBirth, formatter);
  }
}