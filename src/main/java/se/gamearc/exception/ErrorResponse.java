package se.gamearc.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
  private String message;
  private String details;
  private LocalDateTime timestamp;

  public ErrorResponse(String message, String details) {
    this.message = message;
    this.details = details;
    this.timestamp = LocalDateTime.now();
  }
}
