package com.simonamilosheska.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class SecurityExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

  private static final Logger log = LoggerFactory.getLogger(SecurityExceptionHandler.class);

  @ExceptionHandler(AccessDeniedException.class)
  @Override
  public void handle(
    HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws
    IOException {
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    String errorMessage = "You are not authorized to perform this action";
    log.error("Caught exception: ", accessDeniedException);

    response.getWriter().write(errorMessage);
  }

  @Override
  public void commence(
    HttpServletRequest request, HttpServletResponse response,
    AuthenticationException authException) throws IOException {
    response.addHeader("access_denied_reason", "authentication_required");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    String errorMessage = "You are not authenticated to perform this action, please login";
    log.error("Caught exception: ", authException);
    response.getWriter().write(errorMessage);
  }
}
