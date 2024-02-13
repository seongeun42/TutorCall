package com.potato.TutorCall.chat.repository.chat;

import com.potato.TutorCall.chat.domain.ChatMessage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveCrudRepository<ChatMessage, String> {
  Flux<ChatMessage> findAllByChatroomIdOrderByCreatedAt(String roomId);
}
