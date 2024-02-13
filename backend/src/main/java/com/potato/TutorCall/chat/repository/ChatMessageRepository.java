package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.ChatMessage;
import org.springframework.data.repository.CrudRepository;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {}
