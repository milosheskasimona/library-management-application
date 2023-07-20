package com.simonamilosheska;

import com.simonamilosheska.controllers.AuthorController;
import com.simonamilosheska.controllers.ClientController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplicationSpringApplication {

  private AuthorController authorController;
  private ClientController clientController;


  @Autowired
  public LibraryManagementApplicationSpringApplication(
    AuthorController authorController, ClientController clientController) {
    this.authorController = authorController;
    this.clientController = clientController;
  }

  public static void main(String[] args) {
    SpringApplication.run(LibraryManagementApplicationSpringApplication.class, args);
  }

}
