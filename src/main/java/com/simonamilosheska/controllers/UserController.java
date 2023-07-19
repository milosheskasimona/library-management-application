package com.simonamilosheska.controllers;

import com.simonamilosheska.requests.AuthenticationRequest;
import com.simonamilosheska.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.simonamilosheska.constants.SecurityConstants.JWT_COOKIE_NAME;

@CrossOrigin
@RestController
public class UserController {

  private final UserServiceImpl userServiceImpl;

  @Autowired
  public UserController(UserServiceImpl userServiceImpl) {
    this.userServiceImpl = userServiceImpl;
  }

  @GetMapping("/login")
  public ResponseEntity<Void> getToken(@RequestBody AuthenticationRequest authenticationRequest) {
    HttpCookie cookie = userServiceImpl.authenticateUser(authenticationRequest);
    String cookieString = cookie.toString();
    return ResponseEntity.ok()
                         .header(HttpHeaders.SET_COOKIE, cookieString)
                         .build();
  }
  @RequestMapping(method = RequestMethod.POST, value = "/admin")
  public ResponseEntity<Void> createAdmin(@RequestBody AuthenticationRequest authenticationRequest) {
    userServiceImpl.registerAdmin(authenticationRequest);
    HttpCookie cookie = userServiceImpl.authenticateUser(authenticationRequest);
    String cookieString = cookie.toString();

    return ResponseEntity.status(201)
                         .header(HttpHeaders.SET_COOKIE, cookieString)
                         .build();
  }




  @RequestMapping(method = RequestMethod.POST, value = "/user")
  public ResponseEntity<Void> createUser(@RequestBody AuthenticationRequest authenticationRequest) {
    userServiceImpl.registerUser(authenticationRequest);
    HttpCookie cookie = userServiceImpl.authenticateUser(authenticationRequest);
    String cookieString = cookie.toString();

    return ResponseEntity.status(201)
                         .header(HttpHeaders.SET_COOKIE, cookieString)
                         .build();
  }

  @RequestMapping(value = "/librarian")
  public ResponseEntity<Void> createLibrarian(@RequestBody AuthenticationRequest authenticationRequest) {
    userServiceImpl.registerLibrarian(authenticationRequest);
    HttpCookie cookie = userServiceImpl.authenticateUser(authenticationRequest);
    String cookieString = cookie.toString();

    return ResponseEntity.status(201)
                         .header(HttpHeaders.SET_COOKIE, cookieString)
                         .build();
  }
}