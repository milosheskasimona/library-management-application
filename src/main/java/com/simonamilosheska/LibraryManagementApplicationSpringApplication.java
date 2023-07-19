package com.simonamilosheska;

import com.simonamilosheska.controllers.AuthorControllerImpl;
import com.simonamilosheska.controllers.ClientControllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplicationSpringApplication {

  private AuthorControllerImpl authorControllerImpl;
  private ClientControllerImpl clientControllerImpl;


  @Autowired
  public LibraryManagementApplicationSpringApplication(
    AuthorControllerImpl authorControllerImpl, ClientControllerImpl clientControllerImpl) {
    this.authorControllerImpl = authorControllerImpl;
    this.clientControllerImpl = clientControllerImpl;
  }

  public static void main(String[] args) {
    SpringApplication.run(LibraryManagementApplicationSpringApplication.class, args);
  }

}
