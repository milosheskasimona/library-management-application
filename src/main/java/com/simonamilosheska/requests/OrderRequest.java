package com.simonamilosheska.requests;

import java.util.List;

public class OrderRequest {
  private int clientId;
  private List<Integer> bookIds;

  public OrderRequest(int clientId, List<Integer> bookIds) {
    this.clientId=clientId;
    this.bookIds=bookIds;
  }

  public int getClientId() {
    return clientId;
  }

  public List<Integer> getBookIds() {
    return bookIds;
  }
}
