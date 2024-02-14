package com.potato.TutorCall.chat.dto.req;

import com.potato.TutorCall.chat.domain.enums.ChatroomType;
import lombok.Data;

import java.util.List;

@Data
public class CreateRoomReqDto {
  String roomName;
  ChatroomType chatroomType;
  List<Long> participants;
}
