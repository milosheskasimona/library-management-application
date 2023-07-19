package com.simonamilosheska.services;

import com.simonamilosheska.responses.AuthorDto;
import com.simonamilosheska.exceptions.AlreadyExistEntityException;
import com.simonamilosheska.exceptions.NotExistEntityException;
import com.simonamilosheska.models.Author;
import com.simonamilosheska.repositories.AuthorRepository;
import com.simonamilosheska.requests.AuthorRequest;
import com.simonamilosheska.services.interfaces.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

  @Autowired
  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public List<AuthorDto> getAllAuthors() {
    return authorRepository.findAll().stream().map(AuthorDto::new).collect(Collectors.toList());
  }

  @Override
  public AuthorDto findAuthorById(Integer id) {
    Author author = getAuthorIfPresentOrError(id);
    return new AuthorDto(author);
  }

  @Override
  public Author saveAuthor(AuthorRequest authorRequest) {
    validateAuthorByEmail(authorRequest.getEmail());
    Author author = new Author(authorRequest.getName(), authorRequest.getEmail(), authorRequest.getDateOfBirth());
    return authorRepository.save(author);
  }

  @Override
  public void deleteAuthorById(Integer id) {
    getAuthorIfPresentOrError(id);
    authorRepository.deleteById(id);
  }

  @Override
  public void editAuthorById(Integer id, AuthorRequest authorRequest) {
    Author author = getAuthorIfPresentOrError(id);
    author.setName(authorRequest.getName());
    author.setEmail(authorRequest.getEmail());
    author.setDateOfBirth(authorRequest.getDateOfBirth());
    authorRepository.save(author);
  }

  @Override
  public Author getAuthorIfPresentOrError(Integer id) {
    Optional<Author> optionalAuthor = authorRepository.findById(id);
    return optionalAuthor
      .orElseThrow(() -> {
        log.error(String.format("Author with id [%d] not found in database", id));
        return new NotExistEntityException(String.format("Author with id: %d doesn't exist", id));
      });
  }

  @Override
  public void validateAuthorByEmail(String email) {
    Optional<Author> optionalAuthor = authorRepository.findAuthorByEmail(email);
    if (optionalAuthor.isPresent()) {
      log.error(String.format("Author with email [%s] already exist  in database", email));
      throw new AlreadyExistEntityException(
        String.format("Author with email %s already exist", email));
    }
  }
}
