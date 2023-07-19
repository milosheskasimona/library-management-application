//package com.simonamilosheska.services;
//
//import com.simonamilosheska.dtos.AuthorDto;
//import com.simonamilosheska.exceptions.NotExistUserException;
//import com.simonamilosheska.models.Author;
//import com.simonamilosheska.repositories.AuthorRepository;
//import com.simonamilosheska.requests.AuthorRequest;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.time.LocalDate;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.util.AssertionErrors.fail;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AuthorServiceTest {
//
//  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//  @Mock
//  private AuthorRepository authorRepository;
//
//  @InjectMocks
//  private AuthorService authorService;
//
//  @Test
//  public void testGetAllAuthors_success() {
//    List<Author> authors =
//      List.of(new Author(1, "Simona Milosheska", "simona.milosheska", LocalDate.parse("2020-09-09", formatter)));
//
//    when(authorRepository.readAllAuthors()).thenReturn(authors);
//    List<AuthorDto> result = authorService.getAllAuthors();
//
//    assertEquals(result.get(0).getEmail(), authors.get(0).getEmail());
//    verify(authorRepository, times(1)).readAllAuthors();
//  }
//
//  @Test
//  public void testGetAuthorById_success() {
//    Author author = new Author(1, "Simona Milosheska", "simona.milosheska", LocalDate.parse("2020-09-09", formatter));
//    when(authorRepository.getAuthorById(author.getId())).thenReturn(author);
//
//    AuthorDto authorDto = authorService.getAuthorById(1);
//
//    assertEquals(author.getEmail(), authorDto.getEmail());
//  }
//
//  @Test(expected = NotExistUserException.class)
//  public void testGetAuthorById_exception() {
//    Author author = new Author(1, "Simona Milosheska", "simona.milosheska", LocalDate.now());
//
//    when(authorRepository.getAuthorById(author.getId())).thenThrow(new NotExistUserException("Not found exception"));
//    authorService.getAuthorById(author.getId());
//
//    fail("Expected exception but none was thrown");
//    verify(authorRepository).getAuthorById(1);
//  }
//
//  @Test
//  public void testAddAuthor_success() {
//    AuthorRequest authorRequest = new AuthorRequest("Simona Milosheska", "simona.milosheska", "2020-09-09");
//    Author author = new Author("Simona Milosheska", "simona.milosheska", LocalDate.parse("2020-09-09", formatter));
//    Author author1 = authorService.addAuthor(authorRequest);
//    assertEquals(author.getEmail(), author1.getEmail());
//  }
//
//
//  @Test
//  public void testUpdateAuthor_success() {
//
//    AuthorRequest authorRequest = new AuthorRequest("Simona Milosheska", "simona.milosheska", "1999-09-09");
//    Author author = new Author("Simona Milosheska", "simona.milosheska", LocalDate.now());
//    AuthorDto author1 = authorService.updateAuthor(authorRequest, 1);
//    assertEquals(author.getEmail(), author1.getEmail());
//  }
//
//  @Test(expected = NotExistUserException.class)
//  public void testUpdateAuthor_exception() {
//    Author author = new Author("Simona Milosheska", "simona.milosheska", LocalDate.now());
//    when(authorRepository.getAuthorById(1)).thenThrow(new NotExistUserException("Not found exception"));
//    AuthorRequest authorRequest = new AuthorRequest("Simona Milosheska", "simona.milosheska", "1999-09-09");
//    authorService.updateAuthor(authorRequest, 1);
//    fail("Expected exception but none was thrown");
//    verify(authorRepository).updateAuthor(author);
//  }
//
//
//}