package com.potato.TutorCall.chat.dto.req;

import lombok.Data;

@Data
public class SendChatReq {
  Long senderId;
  String chatroomId;
  String message;
}
