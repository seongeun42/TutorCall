package com.potato.TutorCall.chat.repository.chatroom;

import com.potato.TutorCall.chat.domain.Chatroom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
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
