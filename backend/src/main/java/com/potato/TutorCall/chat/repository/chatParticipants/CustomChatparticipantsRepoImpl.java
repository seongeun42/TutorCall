package com.potato.TutorCall.chat.repository.chatParticipants;

import com.potato.TutorCall.chat.domain.ChatParticipants;
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
public class CustomChatparticipantsRepoImpl implements CustomChatparticipantsRepository {
  private static final String KEY = "chatting_user";
  private final ReactiveHashOperations<String, String, ChatParticipants> hashOperations;

  @Autowired
  public CustomChatparticipantsRepoImpl(ReactiveRedisOperations<String, ChatParticipants> redisOperations) {
    this.hashOperations = redisOperations.opsForHash();
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
