package com.simonamilosheska.handlers;

import com.simonamilosheska.exceptions.AlreadyExistEntityException;
import com.simonamilosheska.exceptions.NotExistEntityException;
import com.simonamilosheska.exceptions.ZeroNumberOfBooksException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(NotExistEntityException.class)
  public ResponseEntity<String> handleNotExistUserException(NotExistEntityException exception) {
    log.error("Caught exception: ");
    String error = exception.getMessage();
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AlreadyExistEntityException.class)
  public ResponseEntity<String> handleAlreadyExistUserException(AlreadyExistEntityException exception) {
    log.error("Caught exception: ");
    String error = exception.getMessage();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ZeroNumberOfBooksException.class)
  public ResponseEntity<String> handleZeroNumberOfBooksException(ZeroNumberOfBooksException exception) {
    log.error("Caught exception: ", exception);
    String error = exception.getMessage();
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException exception) {
    log.error("Caught exception: ", exception);
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleUnexpectedException(Exception exception) {
    log.error("Caught exception: ", exception);

    String error = "Something went wrong";

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException exception) {
    log.error("Caught exception: ", exception);

    String errorMessage = "You are not authorized to perform this action";
    return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
  }
}
