package com.simonamilosheska.services.interfaces;

import com.simonamilosheska.dtos.AuthorDto;
import com.simonamilosheska.models.Author;
import com.simonamilosheska.requests.AuthorRequest;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

  List<AuthorDto> getAllAuthors();

  AuthorDto findAuthorById(Integer id);

  Author saveAuthor(AuthorRequest authorRequest);

  void deleteAuthorById(Integer id);

  void editAuthorById(Integer id, AuthorRequest authorRequest);

  Author getAuthorIfPresentOrError(Integer id);

  void validateAuthorByEmail(String email);

}
