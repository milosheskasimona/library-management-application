package com.simonamilosheska.services;

import com.simonamilosheska.responses.ClientDto;
import com.simonamilosheska.exceptions.AlreadyExistEntityException;
import com.simonamilosheska.exceptions.NotExistEntityException;
import com.simonamilosheska.models.Client;
import com.simonamilosheska.repositories.ClientRepository;
import com.simonamilosheska.requests.ClientRequest;
import com.simonamilosheska.services.interfaces.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);


  @Autowired
  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public List<ClientDto> getAllClients() {
    return clientRepository.findAll().stream().map(ClientDto::new).collect(Collectors.toList());
  }

  @Override
  public ClientDto findClientById(Integer id) {
    Optional<Client> clientOptional = clientRepository.findById(id);
    if (clientOptional.isPresent()) {
      return new ClientDto(clientOptional.get());
    } else {
      log.error(String.format("Client with id [%d] not found in database", id));
      throw new NotExistEntityException(String.format("Client with id: %d doesn't exist", id));
    }
  }

  @Override
  public Client saveClient(ClientRequest clientRequest) {
    if (clientRepository.findClientByEmail(clientRequest.getEmail()).isPresent()) {
      log.error(String.format("Client with email [%s] already exist  in database", clientRequest.getEmail()));
      throw new AlreadyExistEntityException(
        String.format("Client with email %s already exist", clientRequest.getEmail()));
    }
    Client client = new Client(clientRequest.getName(), clientRequest.getEmail(), clientRequest.getDateOfBirth());
    return clientRepository.save(client);
  }

  @Override
  public void deleteClientById(Integer id) {
    Optional<Client> optionalClient=clientRepository.findById(id);
    if (optionalClient.isPresent()){
      clientRepository.deleteById(id);
    }
    else {
      log.error(String.format("Client with id [%d] not found in database", id));
      throw new NotExistEntityException(String.format("Client with id: %d doesn't exist", id));
    }  }

  @Override
  public void editClientById(Integer id, ClientRequest clientRequest) {
    Optional<Client> optionalClient = clientRepository.findById(id);
    if (optionalClient.isPresent()) {
      Client client = optionalClient.get();
      client.setName(clientRequest.getName());
      client.setEmail(clientRequest.getEmail());
      client.setDateOfBirth(clientRequest.getDateOfBirth());
      clientRepository.save(client);
    }else {
      log.error(String.format("Client with id [%d] not found in database", id));
      throw new NotExistEntityException(String.format("Client with id: %d doesn't exist", id));

    }
  }
}
