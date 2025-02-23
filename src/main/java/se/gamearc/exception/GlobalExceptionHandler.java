package se.gamearc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidArgumentException.class)
  public ResponseEntity<ErrorResponse> handleInvalidArgumentException(InvalidArgumentException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(InvalidJwtAuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }
}
