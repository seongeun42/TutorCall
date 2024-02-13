package com.potato.TutorCall.chat.service;

import com.potato.TutorCall.chat.dto.req.SendChatReq;
import com.potato.TutorCall.chat.dto.res.ChatResDto;
import com.potato.TutorCall.chat.repository.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatMessageRepository chatMessageRepository;

  public Flux<ChatResDto> receiveChatsInRoom(String roomId) {
    return chatMessageRepository.findAllByChatroomIdOrderByCreatedAt(roomId).map(c -> ChatResDto.builder()
            .senderId(c.getSenderId())
            .message(c.getMessage())
            .build());
  }

  // TODO: 소캣 붙여아함...
  public void sendChatToRoom(SendChatReq newChat) {

  }
}
