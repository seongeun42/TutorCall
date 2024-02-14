package com.potato.TutorCall.chat.repository.chatroom;

import com.potato.TutorCall.chat.domain.Chatroom;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class ChatroomRepoImpl implements ChatroomRepository{
  private static final String KEY = "chatroom";
  private final ReactiveHashOperations<String, String, Chatroom> hashOperations;

  @Autowired
  public ChatroomRepoImpl(ReactiveRedisOperations<String, Chatroom> redisOperations) {
    this.hashOperations = redisOperations.opsForHash();
  }

  @Override
  public Mono<Chatroom> save(Chatroom chatroom) {
    return hashOperations.put(KEY, chatroom.getId(), chatroom).map(isSaved -> chatroom);
  }

  @Override
  @Deprecated
  public <S extends Chatroom> Flux<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  @Deprecated
  public <S extends Chatroom> Flux<S> saveAll(Publisher<S> entityStream) {
    return null;
  }

  @Override
  public Mono<Chatroom> findById(String id) {
    return hashOperations.get(KEY, id);
  }

  @Override
  @Deprecated
  public Mono<Chatroom> findById(Publisher<String> id) {
    return null;
  }

  @Override
  public Mono<Boolean> existsById(String id) {
    return hashOperations.hasKey(KEY, id);
  }

  @Override
  @Deprecated
  public Mono<Boolean> existsById(Publisher<String> id) {
    return null;
  }

  @Override
  public Flux<Chatroom> findAll() {
    return hashOperations.values(KEY);
  }

  @Override
  @Deprecated
  public Flux<Chatroom> findAllById(Iterable<String> strings) {
    return null;
  }

  @Override
  @Deprecated
  public Flux<Chatroom> findAllById(Publisher<String> idStream) {
    return null;
  }

  @Override
  public Mono<Long> count() {
    return hashOperations.values(KEY).count();
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return hashOperations.remove(KEY, id).then();
  }

  @Override
  @Deprecated
  public Mono<Void> deleteById(Publisher<String> id) {
    return null;
  }

  @Override
  public Mono<Void> delete(Chatroom chatroom) {
    return deleteById(chatroom.getId());
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
    return null;
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAll(Iterable<? extends Chatroom> entities) {
    return null;
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAll(Publisher<? extends Chatroom> entityStream) {
    return null;
  }

  @Override
  public Mono<Void> deleteAll() {
    return hashOperations.delete(KEY).then();
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
