package com.potato.TutorCall.chat.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatResDto {
  Long senderId;
  String message;

  @Builder
  public ChatResDto(Long senderId, String message) {
    this.senderId = senderId;
    this.message = message;
  }
}
