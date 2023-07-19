package com.simonamilosheska.responses;

import com.simonamilosheska.models.Author;

import java.time.LocalDate;

public class AuthorDto {

  private final int id;
  private String name;
  private String email;
  private LocalDate dateOfBirth;


  public AuthorDto(Author author){
    this.id=author.getId();
    this.name=author.getName();
    this.email=author.getEmail();
    this.dateOfBirth=author.getDateOfBirth();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
