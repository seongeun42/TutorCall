package com.potato.TutorCall.chat.dto.req;

import lombok.Getter;

@Getter
public class SendChatReq {
  Long chatroomId;
  String message;
}
