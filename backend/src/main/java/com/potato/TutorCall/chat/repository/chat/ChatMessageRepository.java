package com.potato.TutorCall.chat.repository.chat;

import com.potato.TutorCall.chat.domain.ChatMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {
  List<ChatMessage> findAllByChatroomIdOrderByCreatedAt(String roomId);
}
