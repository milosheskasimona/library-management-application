package com.simonamilosheska.exceptions;

public class ZeroNumberOfBooksException extends RuntimeException {

  public ZeroNumberOfBooksException(int id, int num) {
    super(String.format("Book with id: [%d] have [%d] copies", id, num));
  }
}
