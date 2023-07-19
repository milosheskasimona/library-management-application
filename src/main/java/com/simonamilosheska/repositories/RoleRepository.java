package com.simonamilosheska.repositories;

import com.simonamilosheska.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
  Optional<Role> getUserRoleByName(String role);

}
