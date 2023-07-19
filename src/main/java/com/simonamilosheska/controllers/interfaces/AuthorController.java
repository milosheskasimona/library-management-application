package com.simonamilosheska.controllers.interfaces;

import com.simonamilosheska.dtos.AuthorDto;
import com.simonamilosheska.requests.AuthorRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorController {

  ResponseEntity<List<AuthorDto>> getAllAuthors();

  ResponseEntity<AuthorDto> getAuthor(Integer id);

  ResponseEntity<Void> createAuthor(AuthorRequest authorRequest);

  ResponseEntity<Void> editAuthor(AuthorRequest authorRequest, Integer id);

  ResponseEntity<Void> deleteAuthor(Integer id);
}