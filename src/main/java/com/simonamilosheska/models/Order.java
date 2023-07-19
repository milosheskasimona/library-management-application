//package com.simonamilosheska.models;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Table(name = "order", schema = "libraryappsh")
//public class Order {
//  @Id
//  @Column(name = "id")
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private int id;
//
//  private int clientId;
//  private List<Integer> bookIds;
//  private LocalDate issueDate;
//  private LocalDate dueDate;
//
//  public Order(int id, int clientId, List<Integer> bookIds, LocalDate issueDate, LocalDate dueDate) {
//    this.id = id;
//    this.clientId = clientId;
//    this.bookIds = bookIds;
//    this.issueDate = issueDate;
//    this.dueDate = dueDate;
//  }
//
//  public Order(int clientId, List<Integer> bookIds, LocalDate issueDate, LocalDate dueDate) {
//    this.clientId = clientId;
//    this.bookIds = bookIds;
//    this.issueDate = issueDate;
//    this.dueDate = dueDate;
//  }
//
//  public Order() {
//
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public int getClientId() {
//    return clientId;
//  }
//
//  public void setClientId(int clientId) {
//    this.clientId = clientId;
//  }
//
//  public List<Integer> getBookIds() {
//    return bookIds;
//  }
//
//  public void setBookIds(List<Integer> bookIds) {
//    this.bookIds = bookIds;
//  }
//
//  public LocalDate getIssueDate() {
//    return issueDate;
//  }
//
//  public void setIssueDate(LocalDate issueDate) {
//    this.issueDate = issueDate;
//  }
//
//  public LocalDate getDueDate() {
//    return dueDate;
//  }
//
//  public void setDueDate(LocalDate dueDate) {
//    this.dueDate = dueDate;
//  }
//}
