package com.potato.TutorCall.chat.repository.chatParticipants;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ChatparticipantsRepository extends ReactiveCrudRepository<ChatParticipants, String>, CustomChatparticipantsRepository {
  Mono<Boolean> existsById(String id);
  Mono<Void> deleteByUserIdAndChatroomId(Long userId, String chatroomId);
}
