package com.potato.TutorCall.chat.controller;

import com.potato.TutorCall.chat.dto.req.SendChatReq;
import com.potato.TutorCall.chat.dto.res.ChatResDto;
import com.potato.TutorCall.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/** 개별 채팅 메시지에 대한 컨트롤러 */
@Controller
@RequiredArgsConstructor
public class ChatController {
  private final ChatService chatService;

  /**
   * 특정 채팅방의 모든 메시지 불러오기
   * 아마 처음 로그인하고 방 들어갈 때 한 번 호출할 듯?
   *
   * @param roomId 채팅방의 Id
   * @return
   */
  @MessageMapping("/chat/{roomId}/{userId}")
  @SendTo("/sub/chat/{roomId}/{userId}")
  public List<ChatResDto> receiveChatsInRoom(@DestinationVariable(value = "roomId") String roomId, @DestinationVariable(value = "userId") Long userId) {
    return chatService.receiveChatsInRoom(roomId);
  }

  /**
   * 메시지전송
   *
   * @param newChat 새로운 메시지
   * @return 보낸 메시지를 바로 전달받음
   */
  @MessageMapping("/chat/{roomId}")
  @SendTo("/sub/chat/{roomId}")
  public ChatResDto sendChatToRoom(@RequestBody SendChatReq newChat, @DestinationVariable String roomId) {
    return chatService.sendChatToRoom(newChat, roomId);
  }
}
