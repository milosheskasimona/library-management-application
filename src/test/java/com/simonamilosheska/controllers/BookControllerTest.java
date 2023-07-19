//package com.simonamilosheska.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.simonamilosheska.dtos.BookDto;
//import com.simonamilosheska.models.Book;
//import com.simonamilosheska.requests.BookRequest;
//import com.simonamilosheska.services.BookService;
//import com.simonamilosheska.services.interfaces.ClientService;
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
//import java.util.Collections;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@RunWith(MockitoJUnitRunner.class)
//public class BookControllerTest {
//
//  private static final int BOOK_ID = 1;
//  private static final String BOOK_NAME = "Test";
//  private static final int AUTHOR_ID = 2;
//  private static final String BOOK_DATE = "2002-09-09";
//  private static final int NUMBER_OF_COPIES = 3;
//
//  private MockMvc mockMvc;
//  private Book book;
//  private BookDto bookDto;
//  private BookRequest bookRequest;
//
//  @Mock
//  private BookService bookService;
//
//  @InjectMocks
//  private BookController bookController;
//
//  @Before
//  public void setup() {
//    book = new Book(BOOK_ID, BOOK_NAME, AUTHOR_ID, LocalDate.parse(BOOK_DATE), NUMBER_OF_COPIES);
//    bookDto = new BookDto(BOOK_ID, BOOK_NAME, AUTHOR_ID, BOOK_DATE, NUMBER_OF_COPIES);
//    bookRequest = new BookRequest(BOOK_NAME, AUTHOR_ID, BOOK_DATE, NUMBER_OF_COPIES);
//    mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
//  }
//
//  @Test
//  public void testGetAllBooks() throws Exception {
//    when(bookService.getAllBooks()).thenReturn(Collections.singletonList(bookDto));
//    mockMvc.perform(get("/books"))
//           .andExpect(status().isOk())
//           .andExpect(jsonPath("$[0].id").value(BOOK_ID))
//           .andExpect(jsonPath("$[0].name").value(BOOK_NAME))
//           .andExpect(jsonPath("$[0].authorId").value(AUTHOR_ID))
//           .andExpect(jsonPath("$[0].dateOfPublishing").value(BOOK_DATE))
//           .andExpect(jsonPath("$[0].numberOfCopies").value(NUMBER_OF_COPIES));
//  }
//
//  @Test
//  public void testCreateBook() throws Exception {
//    ObjectMapper objectMapper = new ObjectMapper();
//    String json = objectMapper.writeValueAsString(bookRequest);
//    when(bookService.addBook(any())).thenReturn(book);
//    mockMvc.perform(post("/books")
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .content(json))
//           .andExpect(status().isCreated())
//           .andExpect(header().string("Location", "/books/1"));
//  }
//
//  @Test
//  public void testGetBookById_noExceptions_success() throws Exception {
//
//    when(bookService.getBookById(BOOK_ID)).thenReturn(bookDto);
//
//    mockMvc.perform(get("/books/" + BOOK_ID))
//           .andExpect(status().isOk())
//           .andExpect(jsonPath("$.id").value(BOOK_ID))
//           .andExpect(jsonPath("$.name").value(BOOK_NAME))
//           .andExpect(jsonPath("$.authorId").value(AUTHOR_ID))
//           .andExpect(jsonPath("$.dateOfPublishing").value(BOOK_DATE))
//           .andExpect(jsonPath("$.numberOfCopies").value(NUMBER_OF_COPIES));
//  }
//
//  @Test
//  public void testEditBook_success() throws Exception {
//    ObjectMapper mapper = new ObjectMapper();
//    String json = mapper.writeValueAsString(bookRequest);
//
//    when(bookService.updateBook(any(), eq(BOOK_ID))).thenReturn(bookDto);
//
//    mockMvc.perform(put("/books/" + BOOK_ID)
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .content(json))
//           .andExpect(status().isNoContent());
//  }
//
//  @Test
//  public void testDeleteBook_success() throws Exception {
//    ObjectMapper mapper = new ObjectMapper();
//    String json = mapper.writeValueAsString(bookRequest);
//
//    when(bookService.deleteBook(BOOK_ID)).thenReturn(bookDto);
//
//    mockMvc.perform(delete("/books/" + BOOK_ID))
//           .andExpect(status().isNoContent());
//  }
//}
