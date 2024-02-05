package com.potato.TutorCall.webRTC.dto;

import lombok.Data;

@Data
public class CreateSessionResponseDto {

  private String sessionId;
  private String message;

  public CreateSessionResponseDto(String sessionId, String message) {
    this.sessionId = sessionId;
    this.message = message;
  }
}
