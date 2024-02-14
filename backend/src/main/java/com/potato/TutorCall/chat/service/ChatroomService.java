package com.potato.TutorCall.chat.service;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import com.potato.TutorCall.chat.domain.Chatroom;
import com.potato.TutorCall.chat.dto.req.CreateRoomReqDto;
import com.potato.TutorCall.chat.dto.req.ExitRoomReqDto;
import com.potato.TutorCall.chat.repository.chatParticipants.ChatparticipantsRepository;
import com.potato.TutorCall.chat.repository.chatroom.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatroomService {
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final ChatroomRepository chatroomRepository;
  private final ChatparticipantsRepository chatparticipantsRepository;

  public void createRoom(CreateRoomReqDto createRoomReq) {
    String roomId = UUID.randomUUID().toString();
    Chatroom newChatroom = new Chatroom(roomId, createRoomReq.getRoomName(), createRoomReq.getChatroomType());

    chatroomRepository.save(newChatroom)
            .doOnError(e -> log.error("채팅방 생성 중 오류: ", e))
            .thenMany(Flux.fromIterable(createRoomReq.getParticipants()))
            .flatMap(userId -> {
              ChatParticipants chatParticipant = ChatParticipants.builder()
                      .id(UUID.randomUUID().toString())
                      .chatroomId(roomId)
                      .userId(userId)
                      .build();
              return chatparticipantsRepository.save(chatParticipant)
                      .doOnError(e -> log.error("참가자 저장 중 오류: ", e));
            }).subscribe();
  }


  public Flux<?> getChatroomList(Long userId) {
    return chatparticipantsRepository.getParticipatingRooms(userId);
  }

  public void exitRoom(Long userId, String roomId) {
    chatparticipantsRepository.deleteByUserIdAndChatroomId(userId, roomId)
            .doOnError(e -> log.error("채팅방 퇴장 중 에러: ", e))
            .doOnSuccess(r -> log.info("채팅방 퇴장 성공"))
            .then(chatparticipantsRepository.countUsersInRoom(roomId)
                    .flatMap(remains -> {
                      if (remains.equals(0L)) {
                        return chatroomRepository.deleteById(roomId)
                                .doOnError(e -> log.error("채팅방 삭제 중 에러: ", e))
                                .doOnSuccess(r -> log.info("채팅방 삭제 성공"));
                      }
                      return Mono.empty();
                    })
            ).subscribe();
  }

  public Flux<?> getUsersInChatroom(String roomId) {
    return chatparticipantsRepository.getUsersInChatroom(roomId);
  }
}
