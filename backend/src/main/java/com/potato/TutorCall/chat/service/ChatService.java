package com.potato.TutorCall.chat.service;

import com.potato.TutorCall.chat.domain.ChatMessage;
import com.potato.TutorCall.chat.dto.req.SendChatReq;
import com.potato.TutorCall.chat.dto.res.ChatResDto;
import com.potato.TutorCall.chat.repository.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {
  private final ChatMessageRepository chatMessageRepository;

  public List<ChatResDto> receiveChatsInRoom(String roomId) {
    List<ChatMessage> chatMessages = chatMessageRepository.findAllByChatroomIdOrderByCreatedAt(roomId);

    List<ChatResDto> result = new ArrayList<>();
    for(ChatMessage chatMessage: chatMessages) {
      result.add(ChatResDto.builder()
              .senderId(chatMessage.getSenderId())
              .message(chatMessage.getMessage())
              .build());
    }

    return result;
  }


  public ChatResDto sendChatToRoom(SendChatReq newChat, String roomId) {
    ChatMessage newMessage = ChatMessage.builder()
            .id(UUID.randomUUID().toString())
            .senderId(newChat.getSenderId())
            .chatroomId(roomId)
            .message(newChat.getMessage())
            .build();

    chatMessageRepository.save(newMessage);

    return ChatResDto.builder()
            .senderId(newMessage.getSenderId())
            .message(newMessage.getMessage())
            .build();
  }
}
