package com.potato.TutorCall.chat.service;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import com.potato.TutorCall.chat.domain.Chatroom;
import com.potato.TutorCall.chat.domain.enums.ChatroomType;
import com.potato.TutorCall.chat.dto.req.CreateRoomReqDto;
import com.potato.TutorCall.chat.dto.res.ChatroomInfoResDto;
import com.potato.TutorCall.chat.repository.chatParticipants.ChatparticipantsRepository;
import com.potato.TutorCall.chat.repository.chatroom.ChatroomRepository;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.dto.UserSimpleDto;
import com.potato.TutorCall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChatroomService {
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final ChatroomRepository chatroomRepository;
  private final ChatparticipantsRepository chatparticipantsRepository;
  private final UserRepository userRepository;

  public void createRoom(CreateRoomReqDto createRoomReq) {
    String roomId = UUID.randomUUID().toString();
    Chatroom newChatroom = new Chatroom(roomId, createRoomReq.getRoomName(), createRoomReq.getChatroomType());

    chatroomRepository.save(newChatroom);

    for(Long participant: createRoomReq.getParticipants()) {
      ChatParticipants chatParticipant = ChatParticipants.builder()
              .id(UUID.randomUUID().toString())
              .chatroomId(roomId)
              .userId(participant)
              .build();

      // 새로 생긴 방에 대한 정보를 참여자들에게 보내줌
      String destination = "/chatroom/created" + participant;
      simpMessagingTemplate.convertAndSend(destination, roomId);

      chatparticipantsRepository.save(chatParticipant);

    }
  }

  public List<ChatroomInfoResDto> getChatroomList(Long userId, String type) {
    List<ChatParticipants> participants = chatparticipantsRepository.findAllByUserId(userId);
    return participants.stream().map(c -> {
      String roomId = c.getChatroomId();
      Chatroom chatroom = chatroomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("채팅방이 없음"));

      return ChatroomInfoResDto.builder()
              .id(chatroom.getId())
              .name(chatroom.getName())
              .chatroomType(chatroom.getType())
              .build();
    }).filter(info -> info.getChatroomType().equals(ChatroomType.valueOf(type))).toList();
  }

  public void exitRoom(Long userId, String roomId) {
    chatparticipantsRepository.deleteByUserIdAndChatroomId(userId, roomId);
  }

  public List<UserSimpleDto> getUsersInChatroom(String roomId) {
    List<ChatParticipants> participants = chatparticipantsRepository.findAllByChatroomId(roomId);
    return participants.stream().map(c -> {
      Long userId = c.getUserId();
      User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("사용자 정보가 없습니다."));

      return UserSimpleDto.builder().user(user).build();
    }).toList();
  }
}
