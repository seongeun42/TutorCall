package com.potato.TutorCall.chat.service;

import com.potato.TutorCall.chat.domain.ChatMessage;
import com.potato.TutorCall.chat.dto.req.SendChatReq;
import com.potato.TutorCall.chat.dto.res.ChatResDto;
import com.potato.TutorCall.chat.repository.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatMessageRepository chatMessageRepository;

  public Flux<ChatResDto> receiveChatsInRoom(String roomId) {
    return chatMessageRepository.findAllByChatroomIdOrderByCreatedAt(roomId).map(c -> ChatResDto.builder()
            .senderId(c.getSenderId())
            .message(c.getMessage())
            .createdAt(c.getCreatedAt())
            .build());
  }

  public Mono<?> sendChatToRoom(SendChatReq newChat, String roomId) {
    ChatMessage newMessage = ChatMessage.builder()
            .id(UUID.randomUUID().toString())
            .senderId(newChat.getSenderId())
            .chatroomId(roomId)
            .message(newChat.getMessage())
            .build();

    chatMessageRepository.save(newMessage);

    return Mono.just(ChatResDto.builder()
            .senderId(newMessage.getSenderId())
            .message(newMessage.getMessage())
            .createdAt(newMessage.getCreatedAt())
            .build());
  }
}
