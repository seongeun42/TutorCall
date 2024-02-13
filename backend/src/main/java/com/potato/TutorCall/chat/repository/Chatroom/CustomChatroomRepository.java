package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.Chatroom;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomChatroomRepository {
  Mono<Chatroom> save(Chatroom chatroom);
  Mono<Chatroom> findById(String id);
  Flux<Chatroom> findAll();
  Mono<Void> deleteById(String id);
  Mono<Void> deleteAll();
  Mono<Void> delete(Chatroom chatroom);
  Mono<Long> count();
  Mono<Boolean> existById(String id);
}
