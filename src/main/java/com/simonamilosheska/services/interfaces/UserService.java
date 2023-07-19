package com.simonamilosheska.services.interfaces;

import com.simonamilosheska.models.Role;
import com.simonamilosheska.models.User;
import com.simonamilosheska.requests.AuthenticationRequest;
import org.springframework.http.HttpCookie;

public interface UserService {
   HttpCookie authenticateUser(AuthenticationRequest request);
   User registerUser(AuthenticationRequest request);
   User registerLibrarian(AuthenticationRequest request);
   User registerAdmin(AuthenticationRequest request);

   User setUsernameAndPassword(AuthenticationRequest request, Role userRole);
}
