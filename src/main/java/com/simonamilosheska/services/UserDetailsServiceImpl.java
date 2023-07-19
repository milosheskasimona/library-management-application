package com.simonamilosheska.services;

import com.simonamilosheska.handlers.GlobalExceptionHandler;
import com.simonamilosheska.models.User;
import com.simonamilosheska.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;
  private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
   log.info( "Loading user by username:"+ username);
    return userRepository.findByUsername(username)
                         .orElseThrow(() -> {
                          log.error("User not found with entered username: " + username);
                           return new UsernameNotFoundException("User not found with entered username");
                         });
  }
}