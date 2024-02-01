package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.ChatMessage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends ReactiveCrudRepository<ChatMessage, Long> {}
