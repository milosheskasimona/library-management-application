package com.simonamilosheska.controllers;

import com.simonamilosheska.responses.ClientDto;
import com.simonamilosheska.models.Client;
import com.simonamilosheska.requests.ClientRequest;

import com.simonamilosheska.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ClientControllerImpl  {

  private final ClientServiceImpl clientServiceImpl;

  @Autowired
  public ClientControllerImpl(ClientServiceImpl clientServiceImpl) {
    this.clientServiceImpl = clientServiceImpl;
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @GetMapping("/clients")
  public ResponseEntity<List<ClientDto>> getAllClients() {
    List<ClientDto> clients = clientServiceImpl.getAllClients();
    return ResponseEntity.ok(clients);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @GetMapping("/clients/{id}")
  public ResponseEntity<ClientDto> getClient(@PathVariable Integer id) {
    ClientDto client = clientServiceImpl.findClientById(id);
    return ResponseEntity.ok(client);
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @PostMapping("/clients")
  public ResponseEntity<Void> createClient(@RequestBody @Valid ClientRequest clientRequest) {
    Client client = clientServiceImpl.saveClient(clientRequest);

    URI location = UriComponentsBuilder.fromUriString("/clients/{id}")
                                       .buildAndExpand(client.getId())
                                       .toUri();

    return ResponseEntity.created(location).build();
  }

  @PreAuthorize("hasAnyRole('LIBRARIAN')")
  @PutMapping("/clients/{id}")
  public ResponseEntity<Void> editClient(@RequestBody @Valid ClientRequest clientRequest, @PathVariable Integer id) {
    clientServiceImpl.editClientById(id, clientRequest);
    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/clients/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
    clientServiceImpl.deleteClientById(id);
    return ResponseEntity.noContent().build();
  }
}
