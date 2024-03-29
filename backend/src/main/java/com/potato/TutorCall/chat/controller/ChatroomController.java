package com.potato.TutorCall.chat.controller;

import com.potato.TutorCall.chat.dto.req.CreateRoomReqDto;
import com.potato.TutorCall.chat.dto.req.ExitRoomReqDto;
import com.potato.TutorCall.chat.dto.res.ChatroomInfoResDto;
import com.potato.TutorCall.chat.service.ChatroomService;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

import java.util.List;

/** 채팅방에 대한 컨트롤러 */
@Controller
@RequiredArgsConstructor
public class ChatroomController {
  private final ChatroomService chatroomService;

  /**
   * 사용자가 참여한 채팅방의 목록을 반환
   *
   * @param userId 사용자의 id
   * @param type 채팅방 타입(1:1 또는 단체)
   * @return
   */
  @MessageMapping("/chatroom/{userId}/{type}")
  @SendTo("/sub/chatroom/{userId}/{type}")
  public List<ChatroomInfoResDto> getChatroomList(@DestinationVariable Long userId, @DestinationVariable String type) {
    return chatroomService.getChatroomList(userId, type);
  }

  /**
   * 채팅방 참여자들의 아이디를 반환
   *
   * @param roomId
   * @return
   */
  @MessageMapping("/chatroom/users/{roomId}")
  @SendTo("/sub/chatroom/users/{roomId}")
  public List<UserSimpleDto> getUsersInChatroom(@DestinationVariable String roomId) {
    return chatroomService.getUsersInChatroom(roomId);
  }

  /**
   * 채팅방 생성
   * /chatroom/created/{userId}로 새로 생성된 채팅방의 roomId가 날아감
   * @param createRoomReq
   */
  @MessageMapping("/chatroom/created")
  public void createRoom(@RequestBody CreateRoomReqDto createRoomReq) {
    chatroomService.createRoom(createRoomReq);
  }

  /**
   * 채팅방에서 퇴장
   *
   * @param exitRoomReq
   */
  @MessageMapping("/chatroom/exited/{userId}")
  @SendTo("/sub/chatroom/exited/{userId}")
  public void exitRoom(@DestinationVariable Long userId, @RequestBody ExitRoomReqDto exitRoomReq) {
    chatroomService.exitRoom(userId, exitRoomReq.getRoomId());
  }

}
