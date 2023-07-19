package com.simonamilosheska.responses;

import com.simonamilosheska.models.Client;

public class ClientDto {

  private int id;
  private String name;
  private String email;
  private String dateOfBirth;

  public ClientDto(int id, String name, String email, String dateOfBirth) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }

  public ClientDto(Client client){
    this.id=client.getId();
    this.name = client.getName();
    this.email = client.getEmail();
    this.dateOfBirth = client.getDateOfBirth().toString();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
