package com.simonamilosheska.repositories;

import com.simonamilosheska.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

  Optional<Client> findClientByEmail(String email);

}
