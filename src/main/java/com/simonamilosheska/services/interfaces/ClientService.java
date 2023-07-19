package com.simonamilosheska.services.interfaces;

import com.simonamilosheska.responses.ClientDto;
import com.simonamilosheska.models.Client;
import com.simonamilosheska.requests.ClientRequest;

import java.util.List;

public interface ClientService {

  List<ClientDto> getAllClients();

  ClientDto findClientById(Integer id);

  Client saveClient(ClientRequest clientRequest);

  void deleteClientById(Integer id);

  void editClientById(Integer id, ClientRequest clientRequest);
}
