package com.simonamilosheska.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book", schema = "libraryappsh")
public class Book implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name")
  private String name;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "authorid", referencedColumnName = "id")
  private Author author;
  @Column(name = "dateofpublishing")
  private LocalDate dateOfPublishing;
  @Column(name = "numberofcopies")
  private int numberOfCopies;

  public Book(int id, String name, Author author, LocalDate dateOfPublishing, int numberOfCopies) {
    this.id = id;
    this.name = name;
    this.author = author;
    this.dateOfPublishing = dateOfPublishing;
    this.numberOfCopies = numberOfCopies;
  }

  public Book(String name, Author author, LocalDate dateOfPublishing, int numberOfCopies) {
    this.name = name;
    this.author = author;
    this.dateOfPublishing = dateOfPublishing;
    this.numberOfCopies = numberOfCopies;
  }

  public Book() {
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

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public LocalDate getDateOfPublishing() {
    return dateOfPublishing;
  }

  public void setDateOfPublishing(LocalDate dateOfPublishing) {
    this.dateOfPublishing = dateOfPublishing;
  }

  public int getNumberOfCopies() {
    return numberOfCopies;
  }

  public void setNumberOfCopies(int numberOfCopies) {
    this.numberOfCopies = numberOfCopies;
  }

  @Override
  public String toString() {
    return String.format("%-10d %-15s %-15tF %-15d", getId(), getName(), getDateOfPublishing(),
                         getNumberOfCopies());
  }
}
