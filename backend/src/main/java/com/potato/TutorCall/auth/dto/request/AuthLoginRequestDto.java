package com.potato.TutorCall.auth.dto.request;


import lombok.Data;

@Data
public class AuthLoginRequestDto {
    private String email;
    private String password;
}
