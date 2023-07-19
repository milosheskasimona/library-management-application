//package com.simonamilosheska.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.simonamilosheska.dtos.AuthorDto;
//import com.simonamilosheska.models.Author;
//import com.simonamilosheska.requests.AuthorRequest;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Collections;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@RunWith(MockitoJUnitRunner.class)
//public class AuthorControllerTest {
//
//  private static final int AUTHOR_ID = 1;
//  private static final String AUTHOR_NAME = "Test";
//  private static final String AUTHOR_EMAIL = "test@test";
//  private static final String AUTHOR_DATE = "2002-09-09";
//  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//  private MockMvc mockMvc;
//  private Author author;
//  private AuthorDto authorDto;
//  private AuthorRequest authorRequest;
//
//  @Mock
//  private AuthorService authorService;
//
//  @InjectMocks
//  private AuthorController authorController;
//
//  @Before
//  public void setup() {
//    author = new Author(AUTHOR_ID, AUTHOR_NAME, AUTHOR_EMAIL, LocalDate.parse(AUTHOR_DATE));
//    authorDto = new AuthorDto(AUTHOR_ID, AUTHOR_NAME, AUTHOR_EMAIL, AUTHOR_DATE);
//    authorRequest = new AuthorRequest(AUTHOR_NAME, AUTHOR_EMAIL, AUTHOR_DATE);
//    mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
//  }
//
//  @Test
//  public void testGetAllAuthors() throws Exception {
//    when(authorService.getAllAuthors()).thenReturn(Collections.singletonList(authorDto));
//    mockMvc.perform(get("/authors"))
//           .andExpect(status().isOk())a
//           .andExpect(jsonPath("$[0].id").value(AUTHOR_ID))
//           .andExpect(jsonPath("$[0].name").value(AUTHOR_NAME))
//           .andExpect(jsonPath("$[0].email").value(AUTHOR_EMAIL))
//           .andExpect(jsonPath("$[0].dateOfBirth").value(AUTHOR_DATE));
//  }
//
//  @Test
//  public void testCreateAuthor() throws Exception {
//    ObjectMapper objectMapper = new ObjectMapper();
//    String json = objectMapper.writeValueAsString(authorRequest);
//    when(authorService.addAuthor(any())).thenReturn(author);
//    mockMvc.perform(post("/authors")
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .content(json))
//           .andExpect(status().isCreated())
//           .andExpect(header().string("Location", "/authors/1"));
//  }
//
//  @Test
//  public void testGetAuthorById_noExceptions_success() throws Exception {
//
//    when(authorService.getAuthorById(AUTHOR_ID)).thenReturn(authorDto);
//
//    mockMvc.perform(get("/authors/" + AUTHOR_ID))
//           .andExpect(status().isOk())
//           .andExpect(jsonPath("$.id").value(AUTHOR_ID))
//           .andExpect(jsonPath("$.name").value(AUTHOR_NAME))
//           .andExpect(jsonPath("$.email").value(AUTHOR_EMAIL))
//           .andExpect(jsonPath("$.dateOfBirth").value(AUTHOR_DATE));
//  }
//
//  @Test
//  public void testEditAuthor_success() throws Exception {
//    ObjectMapper mapper = new ObjectMapper();
//    String json = mapper.writeValueAsString(authorRequest);
//
//    when(authorService.updateAuthor(any(), eq(AUTHOR_ID))).thenReturn(authorDto);
//
//    mockMvc.perform(put("/authors/" + AUTHOR_ID)
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .content(json))
//           .andExpect(status().isNoContent());
//  }
//
////  @Test
////  public void testDeleteAuthor_success() throws Exception {
////    ObjectMapper mapper = new ObjectMapper();
////    String json = mapper.writeValueAsString(authorRequest);
////
////    when(authorService.deleteAuthor(AUTHOR_ID)).thenReturn(authorDto);
////
////    mockMvc.perform(delete("/authors/" + AUTHOR_ID))
////           .andExpect(status().isNoContent());
////  }
//
//
//}
