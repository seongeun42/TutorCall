package com.potato.TutorCall.chat.repository.chat;

import com.potato.TutorCall.chat.domain.ChatMessage;
import com.potato.TutorCall.chat.domain.ChatParticipants;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Repository
@Slf4j
public class ChatMessageRepoImpl implements ChatMessageRepository {
  private static final String KEY = "chat";
  private final ReactiveHashOperations<String, String, ChatMessage> hashOperations;

  @Autowired
  public ChatMessageRepoImpl(ReactiveRedisOperations<String, ChatMessage> redisOperations) {
    this.hashOperations = redisOperations.opsForHash();
  }

  @Override
  public Flux<ChatMessage> findAllByChatroomIdOrderByCreatedAt(String roomId) {
    return findAll().filter(c -> c.getChatroomId().equals(roomId))
            .sort(Comparator.comparing(ChatMessage::getCreatedAt));
  }

  @Override
  public Mono<ChatMessage> save(ChatMessage chatMessage) {
    return findById(chatMessage.getId())
            .flatMap(c -> Mono.defer(() -> {
              Mono<Boolean> exists = existsById(chatMessage.getId());
              return addOrUpdateChatMessage(chatMessage, exists);
            }));
  }

  @Override
  @Deprecated
  public <S extends ChatMessage> Flux<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  @Deprecated
  public <S extends ChatMessage> Flux<S> saveAll(Publisher<S> entityStream) {
    return null;
  }

  @Override
  public Mono<ChatMessage> findById(String id) {
    return hashOperations.get(KEY, id);
  }

  @Override
  @Deprecated
  public Mono<ChatMessage> findById(Publisher<String> id) {
    return null;
  }

  @Override
  public Mono<Boolean> existsById(String id) {
    return null;
  }

  @Override
  public Mono<Boolean> existsById(Publisher<String> id) {
    return hashOperations.hasKey(KEY, id);
  }

  @Override
  public Flux<ChatMessage> findAll() {
    return hashOperations.values(KEY);
  }

  @Override
  @Deprecated
  public Flux<ChatMessage> findAllById(Iterable<String> strings) {
    return null;
  }

  @Override
  @Deprecated
  public Flux<ChatMessage> findAllById(Publisher<String> idStream) {
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
  public Mono<Void> delete(ChatMessage chatMessage) {
    return deleteById(chatMessage.getId());
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
    return null;
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAll(Iterable<? extends ChatMessage> entities) {
    return null;
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAll(Publisher<? extends ChatMessage> entityStream) {
    return null;
  }

  @Override
  public Mono<Void> deleteAll() {
    return hashOperations.delete(KEY).then();
  }
  private Mono<ChatMessage> addOrUpdateChatMessage(ChatMessage chatMessage, Mono<Boolean> exists) {
    return exists.flatMap(exist -> {
      if(exist) {
        log.error("중복되는 참여자 정보: " + chatMessage.toString());
        return Mono.error(new DuplicateKeyException("이미 존재하는 참여자 ID"));
      } else {
        return hashOperations.put(KEY, chatMessage.getId(), chatMessage).map(isSaved -> chatMessage);
      }
    }).thenReturn(chatMessage);
  }
}
