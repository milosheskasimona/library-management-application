package com.simonamilosheska.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "author" ,  schema = "libraryappsh")

public class Author implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "email", unique = true)
  private String email;
  @Column(name = "dateofbirth")
  private LocalDate dateOfBirth;

  public Author(int id, String name, String email, LocalDate dateOfBirth) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }
  public Author(String name, String email, LocalDate dateOfBirth) {
    this.name = name;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }

  public Author() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  @Override
  public String toString() {
    return String.format("%d %-5s %-5s %-5tF", getId(), getName(), getEmail(), getDateOfBirth());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return id == author.id && Objects.equals(name, author.name) &&
           Objects.equals(email, author.email) && Objects.equals(dateOfBirth, author.dateOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, dateOfBirth);
  }
}
