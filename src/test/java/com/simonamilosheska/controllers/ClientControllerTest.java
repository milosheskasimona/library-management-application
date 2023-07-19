//package com.simonamilosheska.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.simonamilosheska.dtos.ClientDto;
//import com.simonamilosheska.models.Client;
//import com.simonamilosheska.requests.ClientRequest;
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
//public class ClientControllerTest {
//
//  private static final int CLIENT_ID = 1;
//  private static final String CLIENT_NAME = "Test";
//  private static final String CLIENT_EMAIL = "test@test";
//  private static final String CLIENT_DATE = "2002-09-09";
//
//  private MockMvc mockMvc;
//  private Client client;
//  private ClientDto clientDto;
//  private ClientRequest clientRequest;
//
//  @Mock
//  private ClientService clientService;
//
//  @InjectMocks
//  private ClientController clientController;
//
//  @Before
//  public void setup() {
//    client = new Client(CLIENT_ID, CLIENT_NAME, CLIENT_EMAIL, LocalDate.parse(CLIENT_DATE));
//    clientDto = new ClientDto(CLIENT_ID, CLIENT_NAME, CLIENT_EMAIL, CLIENT_DATE);
//    clientRequest = new ClientRequest(CLIENT_NAME, CLIENT_EMAIL, CLIENT_DATE);
//    mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
//  }
//
//  @Test
//  public void testGetAllClients() throws Exception {
//    when(clientService.getAllClients()).thenReturn(Collections.singletonList(clientDto));
//    mockMvc.perform(get("/clients"))
//           .andExpect(status().isOk())
//           .andExpect(jsonPath("$[0].id").value(CLIENT_ID))
//           .andExpect(jsonPath("$[0].name").value(CLIENT_NAME))
//           .andExpect(jsonPath("$[0].email").value(CLIENT_EMAIL))
//           .andExpect(jsonPath("$[0].dateOfBirth").value(CLIENT_DATE));
//  }
//
//  @Test
//  public void testCreateClient() throws Exception {
//    ObjectMapper objectMapper = new ObjectMapper();
//    String json = objectMapper.writeValueAsString(clientRequest);
//    when(clientService.addClient(any())).thenReturn(client);
//    mockMvc.perform(post("/clients")
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .content(json))
//           .andExpect(status().isCreated())
//           .andExpect(header().string("Location", "/clients/1"));
//  }
//
//  @Test
//  public void testGetClientById_noExceptions_success() throws Exception {
//
//    when(clientService.getClientById(CLIENT_ID)).thenReturn(clientDto);
//
//    mockMvc.perform(get("/clients/" + CLIENT_ID))
//           .andExpect(status().isOk())
//           .andExpect(jsonPath("$.id").value(CLIENT_ID))
//           .andExpect(jsonPath("$.name").value(CLIENT_NAME))
//           .andExpect(jsonPath("$.email").value(CLIENT_EMAIL))
//           .andExpect(jsonPath("$.dateOfBirth").value(CLIENT_DATE));
//  }
//
//  @Test
//  public void testEditClient_success() throws Exception {
//    ObjectMapper mapper = new ObjectMapper();
//    String json = mapper.writeValueAsString(clientRequest);
//
//    when(clientService.updateClient(any(), eq(CLIENT_ID))).thenReturn(clientDto);
//
//    mockMvc.perform(put("/clients/" + CLIENT_ID)
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .content(json))
//           .andExpect(status().isNoContent());
//  }
//
//  @Test
//  public void testDeleteClient_success() throws Exception {
//    ObjectMapper mapper = new ObjectMapper();
//    String json = mapper.writeValueAsString(clientRequest);
//
//    when(clientService.deleteClient(CLIENT_ID)).thenReturn(clientDto);
//
//    mockMvc.perform(delete("/clients/" + CLIENT_ID))
//           .andExpect(status().isNoContent());
//  }
//
//
//}
