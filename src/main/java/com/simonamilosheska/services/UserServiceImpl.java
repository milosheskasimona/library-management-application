package com.simonamilosheska.services;

import com.simonamilosheska.exceptions.AlreadyExistEntityException;
import com.simonamilosheska.models.Role;
import com.simonamilosheska.models.User;
import com.simonamilosheska.repositories.RoleRepository;
import com.simonamilosheska.repositories.UserRepository;
import com.simonamilosheska.requests.AuthenticationRequest;
import com.simonamilosheska.services.interfaces.UserService;
import com.simonamilosheska.util.JwtCookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final JwtCookieUtil jwtCookieUtil;
  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(
    UserRepository userRepository, RoleRepository roleRepository, JwtCookieUtil jwtCookieUtil, AuthenticationManager authenticationManager,
    PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.jwtCookieUtil = jwtCookieUtil;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public HttpCookie authenticateUser(AuthenticationRequest request) {
    UserDetails userDetails = (UserDetails) authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    ).getPrincipal();

    return jwtCookieUtil.createJWTCookie(userDetails);
  }

  @Override
  public User registerUser(AuthenticationRequest request)  {
    Role userRole = null;
    try {
      userRole = roleRepository.getUserRoleByName("CLIENT")
                                    .orElseThrow(() -> new RoleNotFoundException("Failed retrieving role \"USER\""));
    } catch (RoleNotFoundException e) {
      e.printStackTrace();
    }
    User user = setUsernameAndPassword(request, userRole);
    userRepository.save(user);
    return user;
  }
  @Override
  public User registerAdmin(AuthenticationRequest request)  {
    Role adminRole = null;
    try {
      adminRole = roleRepository.getUserRoleByName("ADMIN")
                               .orElseThrow(() -> new RoleNotFoundException("Failed retrieving role \"ADMIN\""));
    } catch (RoleNotFoundException e) {
      e.printStackTrace();
    }
    User user = setUsernameAndPassword(request, adminRole);
    userRepository.save(user);
    return user;
  }

  @Override
  public User registerLibrarian(AuthenticationRequest request)  {
    Role librarianRole = null;
    try {
      librarianRole = roleRepository.getUserRoleByName("LIBRARIAN")
                                         .orElseThrow(
                                           () -> new RoleNotFoundException("Failed retrieving role \"LIBRARIAN\""));
    } catch (RoleNotFoundException e) {
      e.printStackTrace();
    }
    User librarian = setUsernameAndPassword(request, librarianRole);
    userRepository.save(librarian);
    return librarian;
  }

  @Override
   public User setUsernameAndPassword(AuthenticationRequest request,Role userRole) {
    userRepository.findByUsername(request.getUsername()).ifPresent(user -> {throw new AlreadyExistEntityException("user exist");});

    String password = request.getPassword();
    String encodedPassword = passwordEncoder.encode(password);
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(encodedPassword);
    user.setRoles(Collections.singletonList(userRole));
    return user;
  }
}