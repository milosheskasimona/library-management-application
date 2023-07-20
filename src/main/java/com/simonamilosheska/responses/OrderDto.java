package com.simonamilosheska.responses;


import com.simonamilosheska.models.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

  private final int id;
  private final ClientDto client;
  private final List<BookDto> books;
  private final LocalDate issueDate;
  private final LocalDate dueDate;

  public OrderDto(Order order) {
    this.id = order.getId();
    this.client = new ClientDto(order.getClient());
    this.books = order.getBooks().stream().map(BookDto::new).collect(Collectors.toList());
    this.issueDate = order.getIssueDate();
    this.dueDate = order.getDueDate();
  }

  public int getId() {
    return id;
  }

  public ClientDto getClient() {
    return client;
  }

  public List<BookDto> getBooks() {
    return books;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }
}
