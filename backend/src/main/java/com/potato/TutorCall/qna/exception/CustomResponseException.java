package com.potato.TutorCall.qna.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CustomResponseException extends RuntimeException{
    HttpStatus httpStatus;
    String message;
}
