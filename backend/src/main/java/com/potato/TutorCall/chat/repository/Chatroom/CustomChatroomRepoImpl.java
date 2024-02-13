package com.potato.TutorCall.chat.repository;

import com.potato.TutorCall.chat.domain.Chatroom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class CustomChatroomRepoImpl implements CustomChatroomRepository {
  private static final String KEY = "chatroom";
  private final ReactiveHashOperations<String, String, Chatroom> hashOperations;

  @Autowired
  public CustomChatroomRepoImpl(ReactiveRedisOperations<String, Chatroom> redisOperations) {
    this.hashOperations = redisOperations.opsForHash();
  }

  @Override
  public Mono<Chatroom> save(Chatroom chatroom) {
    return findById(chatroom.getId()).flatMap(c -> Mono.defer(() -> {
      Mono<Boolean> exists = existById(chatroom.getId());
      return addOrUpdateChatroom(chatroom, exists);
    }));
  }

  @Override
  public Mono<Chatroom> findById(String id) {
    return hashOperations.get(KEY, id);
  }

  @Override
  public Flux<Chatroom> findAll() {
    return hashOperations.values(KEY);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return hashOperations.remove(KEY, id).then();
  }

  @Override
  public Mono<Void> deleteAll() {
    return hashOperations.delete(KEY).then();
  }

  @Override
  public Mono<Void> delete(Chatroom chatroom) {
    return hashOperations.remove(KEY, chatroom.getId()).then();
  }

  @Override
  public Mono<Long> count() {
    return hashOperations.values(KEY).count();
  }

  @Override
  public Mono<Boolean> existById(String id) {
    return hashOperations.hasKey(KEY, id);
  }

  private Mono<Chatroom> addOrUpdateChatroom(Chatroom chatroom, Mono<Boolean> exists) {
    return exists.flatMap(exist -> {
      if(exist) {
        log.error("중복되는 채팅방 ID: " + chatroom.toString());
        return Mono.error(new DuplicateKeyException("이미 존재하는 채팅방 ID"));
      } else {
        return hashOperations.put(KEY, chatroom.getId(), chatroom).map(isSaved -> chatroom);
      }
    }).thenReturn(chatroom);
  }
}
