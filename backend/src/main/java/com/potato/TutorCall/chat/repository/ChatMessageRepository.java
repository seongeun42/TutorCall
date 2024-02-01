package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.ChatMessage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends ListCrudRepository<ChatMessage, Long> {}
