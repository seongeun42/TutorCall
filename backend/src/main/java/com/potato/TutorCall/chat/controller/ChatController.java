package com.potato.TutorCall.chat.controller;

import com.potato.TutorCall.chat.dto.req.SendChatReq;
import com.potato.TutorCall.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
  private final ChatService chatService;

  /**
   * 특정 채팅방에 메시지 불러오기
   *
   * @param roomId 채팅방의 Id
   * @return
   */
  @GetMapping(value = "/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<?> receiveChatsInRoom(@PathVariable Long roomId) {
    return null;
  }

  /**
   * 메시지전송
   *
   * @param newChat 새로운 메시지
   * @return
   */
  @PostMapping
  public Mono<?> sendChatToRoom(@RequestBody SendChatReq newChat) {
    return null;
  }
}
