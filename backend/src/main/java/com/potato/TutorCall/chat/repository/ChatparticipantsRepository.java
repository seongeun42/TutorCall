package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChatparticipantsRepository extends ReactiveCrudRepository<ChatParticipants, String> {
}
