package com.potato.TutorCall.chat.dto.req;

import lombok.Getter;

@Getter
public class ExitRoomReqDto {
  Long userId;
  String roomId;
}
