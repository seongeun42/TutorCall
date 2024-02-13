package com.potato.TutorCall.chat.service;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import com.potato.TutorCall.chat.domain.Chatroom;
import com.potato.TutorCall.chat.dto.req.CreateRoomReqDto;
import com.potato.TutorCall.chat.dto.req.ExitRoomReqDto;
import com.potato.TutorCall.chat.repository.ChatParticipants.ChatparticipantsRepository;
import com.potato.TutorCall.chat.repository.Chatroom.ChatroomRepository;
import com.potato.TutorCall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatroomService {
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final ChatroomRepository chatroomRepository;
  private final ChatparticipantsRepository chatparticipantsRepository;

  public Mono<?> createRoom(CreateRoomReqDto createRoomReq) {
    String roomId = UUID.randomUUID().toString();
    // 새로운 채팅방 생성
    Chatroom newChatroom = new Chatroom(roomId, createRoomReq.getRoomName(), createRoomReq.getChatroomType());
    chatroomRepository.save(newChatroom);

    // 사용자-채팅방 관계 생성
    for(Long userId: createRoomReq.getParticipants()) {
      ChatParticipants chatParticipant = ChatParticipants.builder()
              .id(UUID.randomUUID().toString())
              .chatroomId(roomId)
              .userId(userId)
              .build();

      chatparticipantsRepository.save(chatParticipant);
    }

    return Mono.just(roomId);
  }

  public Flux<?> getChatroomList(Long userId) {
    return chatparticipantsRepository.getParticipatingRooms(userId);
  }

  public void exitRoom(ExitRoomReqDto exitRoomReq) {
    chatparticipantsRepository.deleteByUserIdAndChatroomId(exitRoomReq.getUserId(), exitRoomReq.getRoomId());

    Mono<Long> zeroMono = Mono.just(0L);
    Mono<Long> remains = chatparticipantsRepository.countUsersInRoom(exitRoomReq.getRoomId());

    remains.zipWith(zeroMono).doOnNext(t -> {
      if(t.getT1().equals(t.getT2())) {
        chatroomRepository.deleteById(exitRoomReq.getRoomId());
      }
    }).subscribe();
  }
}
