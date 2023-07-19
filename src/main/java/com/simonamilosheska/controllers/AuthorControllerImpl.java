package com.simonamilosheska.controllers;

import com.simonamilosheska.controllers.interfaces.AuthorController;
import com.simonamilosheska.dtos.AuthorDto;
import com.simonamilosheska.models.Author;
import com.simonamilosheska.requests.AuthorRequest;

import com.simonamilosheska.services.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AuthorControllerImpl implements AuthorController {

  private final AuthorServiceImpl authorServiceImpl;

  @Autowired
  public AuthorControllerImpl(AuthorServiceImpl authorServiceImpl) {
    this.authorServiceImpl = authorServiceImpl;
  }

  @Override
  @GetMapping("/authors")
  public ResponseEntity<List<AuthorDto>> getAllAuthors() {
    List<AuthorDto> authors = authorServiceImpl.getAllAuthors();
    return ResponseEntity.ok(authors);
  }

  @GetMapping("/authors/{id}")
  @Override
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable Integer id) {
    AuthorDto author = authorServiceImpl.findAuthorById(id);
    return ResponseEntity.ok(author);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN','ADMIN')")
  @PostMapping("/authors")
  @Override
  public ResponseEntity<Void> createAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
    Author author = authorServiceImpl.saveAuthor(authorRequest);

    URI location = UriComponentsBuilder.fromUriString("/authors/{id}")
                                       .buildAndExpand(author.getId())
                                       .toUri();

    return ResponseEntity.created(location).build();
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN','ADMIN')")
  @PutMapping("/authors/{id}")
  @Override
  public ResponseEntity<Void> editAuthor(
    @RequestBody @Valid AuthorRequest authorRequest, @PathVariable Integer id) {
     authorServiceImpl.editAuthorById(id, authorRequest);
    return ResponseEntity.noContent().build();
  }

  //deleting when author have book, or client have order ...?
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/authors/{id}")
  @Override
  public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
    authorServiceImpl.deleteAuthorById(id);
    return ResponseEntity.noContent().build();
  }
}
