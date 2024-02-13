package com.potato.TutorCall.chat.repository.ChatParticipants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomChatparticipantsRepository {
  Flux<String> getParticipatingRooms(Long userId);
  Flux<Long> getParticipants(String chatroomId);
  Mono<Long> countUsersInRoom(String roomId);
}
