//package com.simonamilosheska.dtos;
//
//import com.simonamilosheska.models.Order;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class OrderDto {
//
//  private int orderId;
//  private int clientId;
//  private List<Integer> bookIds;
//  private LocalDate issueDate;
//  private LocalDate dueDate;
//
//  public OrderDto(Order order) {
//    this.orderId = order.getId();
//    this.clientId = order.getClientId();
//    this.bookIds = order.getBookIds();
//    this.issueDate = order.getIssueDate();
//    this.dueDate = order.getDueDate();
//  }
//
//
//  public int getOrderId() {
//    return orderId;
//  }
//
//  public int getClientId() {
//    return clientId;
//  }
//
//  public List<Integer> getBookIds() {
//    return bookIds;
//  }
//
//  public LocalDate getIssueDate() {
//    return issueDate;
//  }
//
//  public LocalDate getDueDate() {
//    return dueDate;
//  }
//}
