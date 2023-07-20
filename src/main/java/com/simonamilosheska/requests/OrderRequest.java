package com.simonamilosheska.requests;

import com.simonamilosheska.models.Book;
import com.simonamilosheska.models.Client;

import java.util.List;

public class OrderRequest {
  private Client client;
  private List<Book> books;

  public OrderRequest(Client client, List<Book> books) {
    this.client=client;
    this.books=books;
  }

  public Client getClient() {
    return client;
  }

  public List<Book> getBooks() {
    return books;
  }
}
