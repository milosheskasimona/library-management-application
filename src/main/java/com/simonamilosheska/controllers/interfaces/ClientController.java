package com.simonamilosheska.controllers.interfaces;


import com.simonamilosheska.dtos.ClientDto;
import com.simonamilosheska.requests.ClientRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientController {

  ResponseEntity<List<ClientDto>> getAllClients();

  ResponseEntity<ClientDto> getClient(Integer id);

  ResponseEntity<Void> createClient( ClientRequest clientRequest);

  ResponseEntity<Void> editClient(ClientRequest clientRequest, Integer id);

  ResponseEntity<Void> deleteClient(Integer id);
}
