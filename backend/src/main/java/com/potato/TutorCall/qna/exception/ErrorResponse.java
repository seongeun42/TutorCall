package com.potato.TutorCall.qna.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;
    private int code;

    public static ResponseEntity<ErrorResponse> response(HttpStatus httpStatus, String message){
        return ResponseEntity.status(httpStatus)
                .body(
                        ErrorResponse.builder().code(httpStatus.value())
                                .message(message).build()
                );
    }

}
