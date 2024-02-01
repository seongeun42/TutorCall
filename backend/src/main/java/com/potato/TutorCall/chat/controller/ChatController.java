package com.potato.TutorCall.chat.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.chat.dto.req.SendChatReq;
import com.potato.TutorCall.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** 개별 채팅 메시지에 대한 컨트롤러 */
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
  private final ChatService chatService;

  /**
   * 특정 채팅방에 메시지 불러오기
   *
   * @param userSession 유저의 로그인 세션
   * @param roomId 채팅방의 Id
   * @return
   */
  @GetMapping(value = "/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<?> receiveChatsInRoom(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @PathVariable Long roomId) {
    return null;
  }

  /**
   * 메시지전송
   *
   * @param userSession 유저의 로그인 세션
   * @param newChat 새로운 메시지
   * @return
   */
  @PostMapping
  public Mono<?> sendChatToRoom(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @RequestBody SendChatReq newChat) {
    return null;
  }
}
