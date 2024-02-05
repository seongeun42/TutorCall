package com.potato.TutorCall.webRTC.dto;

import lombok.Data;

@Data
public class ConnectionResponseDto {

    private String token;
    private String message;

    public ConnectionResponseDto(String token, String message) {
        this.token = token;
        this.message = message;
    }

}
