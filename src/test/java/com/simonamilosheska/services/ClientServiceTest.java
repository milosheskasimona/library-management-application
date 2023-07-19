//package com.simonamilosheska.services;
//
//import com.simonamilosheska.dtos.ClientDto;
//import com.simonamilosheska.exceptions.NotExistUserException;
//import com.simonamilosheska.models.Client;
//import com.simonamilosheska.repositories.ClientRepository;
//import com.simonamilosheska.requests.ClientRequest;
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
//import static org.junit.Assert.fail;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ClientServiceTest {
//
//  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//  @Mock
//  private ClientRepository clientRepository;
//
//  @InjectMocks
//  private ClientService clientService;
//
//  @Test
//  public void testGetAllClients_success() {
//    List<Client> clients = List.of(  new Client(1,"Simona Milosheska", "simona.milosheska", LocalDate.parse("2020-09-09", formatter)));
//
//    when(clientRepository.readAllClients()).thenReturn(clients);
//    List<ClientDto> result = clientService.getAllClients();
//
//    assertEquals(result.get(0).getEmail(), clients.get(0).getEmail());
//    verify(clientRepository, times(1)).readAllClients();
//  }
//
//  @Test
//  public void testGetClientById_success() {
//    Client client = new Client(1,"Simona Milosheska", "simona.milosheska", LocalDate.parse("2020-09-09", formatter));
//    when(clientRepository.getClientById(client.getId())).thenReturn(client);
//
//    ClientDto clientDto = clientService.getClientById(1);
//
//    assertEquals(client.getEmail(), clientDto.getEmail());
//  }
//
//  @Test(expected = NotExistUserException.class)
//  public void testGetClientById_exception() {
//    Client client = new Client(1,"Simona Milosheska", "simona.milosheska", LocalDate.now());
//
//    when(clientRepository.getClientById(client.getId())).thenThrow(new NotExistUserException("Not found exception"));
//    clientService.getClientById(client.getId());
//
//    fail("Expected exception but none was thrown");
//    verify(clientRepository).getClientById(1);
//  }
//
//  @Test
//  public void testAddClient_success() {
//
//
//    ClientRequest clientRequest = new ClientRequest("Simona Milosheska", "simona.milosheska", "2020-09-09");
//    Client client = new Client("Simona Milosheska", "simona.milosheska", LocalDate.parse("2020-09-09", formatter));
//    //when(clientRepository.addClient(client)).thenReturn(client);
//    Client client1 = clientService.addClient(clientRequest);
//    assertEquals(client.getEmail(), client1.getEmail());
//  }
//
//
//
//
//  @Test
//  public void testUpdateClient_success() {
//
//    ClientRequest clientRequest = new ClientRequest("Simona Milosheska", "simona.milosheska", "1999-09-09");
//    Client client = new Client("Simona Milosheska", "simona.milosheska", LocalDate.now());
//   // when(clientRepository.updateClient(client, 1)).thenReturn(1);
//    ClientDto client1 = clientService.updateClient(clientRequest, 1);
//    assertEquals(client.getEmail(), client1.getEmail());
//  }
//
//  @Test(expected = NotExistUserException.class)
//  public void testUpdateClient_exception() {
//    Client client = new Client("Simona Milosheska", "simona.milosheska", LocalDate.now());
//    when(clientRepository.getClientById(1)).thenThrow(new NotExistUserException("Not found exception"));
//    ClientRequest clientRequest = new ClientRequest("Simona Milosheska", "simona.milosheska", "1999-09-09");
//    clientService.updateClient(clientRequest, 1);
//    fail("Expected exception but none was thrown");
//    verify(clientRepository).updateClient(client, 1);
//  }
//
//
//}