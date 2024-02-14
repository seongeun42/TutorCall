package com.potato.TutorCall.chat.repository.chatParticipants;

import com.potato.TutorCall.chat.domain.ChatParticipants;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatparticipantsRepository extends ReactiveCrudRepository<ChatParticipants, String> {
  Mono<Void> deleteByUserIdAndChatroomId(Long userId, String chatroomId);
  Flux<String> getParticipatingRooms(Long userId);
  Flux<Long> getUsersInChatroom(String chatroomId);
  Mono<Long> countUsersInRoom(String roomId);
}
