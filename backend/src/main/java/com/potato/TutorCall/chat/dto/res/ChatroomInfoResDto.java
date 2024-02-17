package com.potato.TutorCall.chat.dto.res;

import com.potato.TutorCall.chat.domain.enums.ChatroomType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatroomInfoResDto {
  private String id;
  private String name;
  private ChatroomType chatroomType;
}
