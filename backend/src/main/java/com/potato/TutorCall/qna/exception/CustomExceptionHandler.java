package com.potato.TutorCall.qna.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomResponseException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomResponseException e){
        return ErrorResponse.response(e.getHttpStatus(), e.getMessage());
    }

}
