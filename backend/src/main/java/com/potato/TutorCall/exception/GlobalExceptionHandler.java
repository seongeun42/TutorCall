package com.potato.TutorCall.exception;

import com.potato.TutorCall.exception.customException.ForbiddenException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import javax.naming.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 데이터를 찾을 수 없을 때 발생하는 에러 처리
   *
   * @return Error Response
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND));
  }

  /**
   * 요청이 금지된 경우에 발생하는 에러 처리 e.g. 학생이 쿠폰 발급 요청, 학생이 과외 모집 요청
   *
   * @return Error Response
   */
  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ErrorResponse> forbiddenExceptionHandler(ForbiddenException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN));
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> authenticationExceptionHandler(AuthenticationException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED));
  }

  @ExceptionHandler(ServletRequestBindingException.class)
  public ResponseEntity<ErrorResponse> servletRequestBindingExceptionHandler(
      ServletRequestBindingException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> internalServerErrorHandler(Exception e) {
    log.error(e.getMessage());
    return ResponseEntity.internalServerError()
        .body(new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
  }
}
