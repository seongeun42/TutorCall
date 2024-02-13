package com.potato.TutorCall.chat.repository.chatParticipants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomChatparticipantsRepository {
  Flux<String> getParticipatingRooms(Long userId);
  Flux<Long> getUsersInChatroom(String chatroomId);
  Mono<Long> countUsersInRoom(String roomId);
}
