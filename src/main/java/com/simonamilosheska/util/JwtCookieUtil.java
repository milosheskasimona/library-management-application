package com.simonamilosheska.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.simonamilosheska.constants.SecurityConstants.JWT_COOKIE_NAME;

@Component
public class JwtCookieUtil {

  private final JwtUtil jwtUtil;

  @Autowired
  public JwtCookieUtil(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  public HttpCookie createJWTCookie(UserDetails userDetails) {
    String jwt = jwtUtil.generateToken(userDetails);

    return ResponseCookie.from(JWT_COOKIE_NAME, jwt)
                         .maxAge(jwtUtil.getExpiration())
                         .httpOnly(true)
                         .build();
  }
}