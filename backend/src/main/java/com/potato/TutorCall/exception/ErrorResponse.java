package com.potato.TutorCall.exception;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ErrorResponse {

  private LocalDateTime timestamp;
  private Integer status;
  private HttpStatus error;
  private String message;

  public ErrorResponse(String msg, HttpStatus status) {
    this.timestamp = LocalDateTime.now();
    this.status = status.value();
    this.error = status;
    this.message = msg;
  }
}
