package com.potato.TutorCall.chat.controller;

import com.potato.TutorCall.auth.SessionKey;
import com.potato.TutorCall.auth.dto.UserSessionDto;
import com.potato.TutorCall.chat.dto.req.CreateRoomReqDto;
import com.potato.TutorCall.chat.service.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** 채팅방에 대한 컨트롤러 */
@RestController
@RequestMapping("chatroom")
@RequiredArgsConstructor
public class ChatroomController {
  private final ChatroomService chatroomService;

  /**
   * 사용자가 참여한 채팅방의 목록을 반환
   *
   * @param userSession 유저의 로그인 세션
   * @return
   */
  @GetMapping
  public Flux<?> getChatroomList(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession) {
    return null;
  }

  /**
   * 채팅방 생성
   *
   * @param userSession 유저의 로그인 세션
   * @param createRoomReq
   * @return
   */
  @PostMapping
  public Mono<?> createRoom(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @RequestBody CreateRoomReqDto createRoomReq) {
    return null;
  }

  /**
   * 채팅방에 참가
   *
   * @param userSession 유저의 로그인 세션
   * @param roomId 채팅방 id
   * @return
   */
  @PatchMapping("{roomId}")
  public Mono<?> joinRoom(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @PathVariable Long roomId) {
    return null;
  }

  /**
   * 채팅방에서 퇴장
   *
   * @param userSession 유저의 로그인 세션
   * @param roomId 채팅방 id
   * @return
   */
  @PutMapping("{roomId}")
  public Mono<?> existRoom(
      @SessionAttribute(name = SessionKey.USER) UserSessionDto userSession,
      @PathVariable Long roomId) {
    return null;
  }
}
