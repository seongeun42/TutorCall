package com.potato.TutorCall.chat.controller;

import com.potato.TutorCall.chat.dto.req.CreateRoomReqDto;
import com.potato.TutorCall.chat.dto.req.SendChatReq;
import com.potato.TutorCall.chat.service.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** 채팅방에 대한 컨트롤러 */
@Controller
@RequiredArgsConstructor
public class ChatroomController {
  private final ChatroomService chatroomService;

  /**
   * 사용자가 참여한 채팅방의 목록을 반환
   *
   * @param userId 사용자의 id
   * @return
   */
  @SendTo("/chatroom/{userId}")
  public Flux<?> getChatroomList(@DestinationVariable Long userId) {
    return chatroomService.getChatroomList(userId);
  }

  /**
   * 채팅방 생성
   *
   * @param createRoomReq
   * @return 생성된 방의 ID
   */
  @SendTo("/chatroom/created")
  public Mono<?> createRoom(
      @RequestBody CreateRoomReqDto createRoomReq) {
    return chatroomService.createRoom(createRoomReq);
  }

  /**
   * 방 나가기
   */
  @SendTo("/chatroom/")
  public Mono<?> existRoom()

}
