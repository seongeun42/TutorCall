package com.potato.TutorCall.chat.repository.chatParticipants;

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

@Repository
@Slf4j
public class ChatparticipantsRepoImpl implements ChatparticipantsRepository {
  private static final String KEY = "chatting_user";
  private final ReactiveHashOperations<String, String, ChatParticipants> hashOperations;

  @Autowired
  public ChatparticipantsRepoImpl(ReactiveRedisOperations<String, ChatParticipants> redisOperations) {
    this.hashOperations = redisOperations.opsForHash();
  }

  @Override
  public Mono<Void> deleteByUserIdAndChatroomId(Long userId, String chatroomId) {
    return delete(hashOperations.values(KEY)
            .filter(c -> c.getChatroomId().equals(chatroomId))
            .filter(c -> c.getUserId().equals(userId))
            .singleOrEmpty().block());
  }

  @Override
  public Flux<String> getParticipatingRooms(Long userId) {
    return hashOperations.values(KEY)
            .filter(c -> c.getUserId().equals(userId))
            .map(ChatParticipants::getChatroomId);
  }

  @Override
  public Flux<Long> getUsersInChatroom(String chatroomId) {
    return hashOperations.values(KEY)
            .filter(c -> c.getChatroomId().equals(chatroomId))
            .map(ChatParticipants::getUserId);
  }

  @Override
  public Mono<Long> countUsersInRoom(String roomId) {
    return hashOperations.values(KEY)
            .filter(c -> c.getChatroomId().equals(roomId)).count();
  }

  @Override
  public Mono<ChatParticipants> save(ChatParticipants chatParticipants) {
    return findById(chatParticipants.getId())
            .flatMap(c -> Mono.defer(() -> {
              Mono<Boolean> exists = existsById(chatParticipants.getId());
              return addOrUpdateChatparticipants(chatParticipants, exists);
            }));
  }

  @Override
  @Deprecated
  public <S extends ChatParticipants> Flux<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  @Deprecated
  public <S extends ChatParticipants> Flux<S> saveAll(Publisher<S> entityStream) {
    return null;
  }

  @Override
  public Mono<ChatParticipants> findById(String id) {
    return hashOperations.get(KEY, id);
  }

  @Override
  @Deprecated
  public Mono<ChatParticipants> findById(Publisher<String> id) {
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
  public Flux<ChatParticipants> findAll() {
    return hashOperations.values(KEY);
  }

  @Override
  @Deprecated
  public Flux<ChatParticipants> findAllById(Iterable<String> strings) {
    return null;
  }

  @Override
  @Deprecated
  public Flux<ChatParticipants> findAllById(Publisher<String> idStream) {
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
  @Deprecated
  public Mono<Void> delete(ChatParticipants chatParticipants) {
    return deleteById(chatParticipants.getId());
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
    return null;
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAll(Iterable<? extends ChatParticipants> entities) {
    return null;
  }

  @Override
  @Deprecated
  public Mono<Void> deleteAll(Publisher<? extends ChatParticipants> entityStream) {
    return null;
  }

  @Override
  public Mono<Void> deleteAll() {
    return hashOperations.delete(KEY).then();
  }

  private Mono<ChatParticipants> addOrUpdateChatparticipants(ChatParticipants chatParticipants, Mono<Boolean> exists) {
    return exists.flatMap(exist -> {
      if(exist) {
        log.error("중복되는 참여자 정보: " + chatParticipants.toString());
        return Mono.error(new DuplicateKeyException("이미 존재하는 참여자 ID"));
      } else {
        return hashOperations.put(KEY, chatParticipants.getId(), chatParticipants).map(isSaved -> chatParticipants);
      }
    }).thenReturn(chatParticipants);
  }
}
